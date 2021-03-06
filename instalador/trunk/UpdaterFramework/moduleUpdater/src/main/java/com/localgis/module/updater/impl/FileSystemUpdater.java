/**
 * FileSystemUpdater.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.localgis.module.updater.impl;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.maven.artifact.Artifact;
import org.codehaus.plexus.util.FileUtils;

import com.localgis.module.utilitys.UpdaterUtilities;
import com.localgis.tools.modules.Module;
import com.localgis.tools.modules.exception.DependencyViolationException;

public class FileSystemUpdater extends AbstractLocalGISUpdater
{
	private static String LOCALGIS_JARS_DEPLOYPATH = "localgis_sysWAR_deployPath";
	
	private static String DIR_LIBRARIES_DEPLOY_PATH = ".."+File.separator+"lib"+File.separator+"ext";
	
	private static final String JNLP_FILE_PATH = "jnlp";

	private boolean validInstallation = false;
	private boolean validUninstallation = false;
	
	private boolean libExtUpdater=false;
	
	public FileSystemUpdater(){
		
	}

	public FileSystemUpdater(String tipoInstalacion){
		if (tipoInstalacion.equals("movilidad")){
			LOCALGIS_JARS_DEPLOYPATH="localgis_appWAR_deployPath";
			DIR_LIBRARIES_DEPLOY_PATH="software"+File.separator+"libraries";
		}
		else if (tipoInstalacion.equals("libext")){
			libExtUpdater=true;
		}
	}
	
	public void install() throws DependencyViolationException {
		try {
			installModule(getModule(), getBinaryArtifact());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void uninstall() throws DependencyViolationException {
		try {
			//Desinstalacion modulo enviado
			//uninstallModule(getModule());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void upgrade() throws DependencyViolationException {
		String mensajeBase = getMessageResource("text.operacion.upgrade");
		try {
			this.userInterfaceFacade.notifyActivity(mensajeBase + ": " + getFullFinalNameArtifact(getModule()));
			//Desinstalacion modulo version anterior (al que actuliza el mudulo actual)
			//uninstallModule(getModule().getUpgradableMod());
			//Nueva instalacion
			install();
			showMessageSuccess(mensajeBase);
		}catch (Exception e) {
			showMessageFailed(mensajeBase);
			throw new RuntimeException(e);
		}
	}

	
	public void installOrUpgrade() {
		// TODO Auto-generated method stub
	}

	
	public boolean checkInstallationValidity() {
		// TODO Auto-generated method stub
		return this.validInstallation;
	}

	
	public boolean checkUninstallationValidity() {
		// TODO Auto-generated method stub
		return this.validUninstallation;
	}
	
	
	/**
	 * Instalacion clientes jnlp:
	 * Directorios: 
	 * /<dir_base_deploy>: Directorio base despliegue (destino de ficheros .jnl)
	 * /<dir_base_deploy>/<dir_libraries>: Directorio librerias (destino de fichero .jar)
	 * Pasos:
	 * Copiar <clienteJnlp>.jar  a procesar, en directorio de librerias.
	 * Extraer de <clienteJnlp>.jar fichero <clienteJnlp>.jnlp en directorio base despliegue
	 * Copiar dependencias (NO cargadas) en direcorio de librerias 
	 */
	
	private void installModule(Module module, Artifact binaryArtifact) throws DependencyViolationException
	{
		String mensajeBase = getMessageResource("text.operacion.install");
		try {
			if ((binaryArtifact.getType()!=null) && (binaryArtifact.getType().equalsIgnoreCase("zip"))){
				this.validInstallation = true;
				showMessageSuccess(mensajeBase);
				
			}
			else{
				this.userInterfaceFacade.notifyActivity(mensajeBase + ": " + getFullFinalNameArtifact(module));
				//Rutas destino para despliegue /<dir_base_deploy> y /<dir_base_deploy>/<dir_libraries>
				File targetDirectory = new File(properties.getProperty(LOCALGIS_JARS_DEPLOYPATH));
				File targetLibraryDirectory = new File(targetDirectory, DIR_LIBRARIES_DEPLOY_PATH);
				//Desplegar aplicacion
				deployClientJars(targetDirectory, targetLibraryDirectory, binaryArtifact);
				//Carga dependencias
				loadDependencies(targetLibraryDirectory, dependencies);
				this.validInstallation = true;
				showMessageSuccess(mensajeBase);
			}
		}catch (Exception e) {
			showMessageFailed(mensajeBase);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Desintalar cliente JNLP asociado al modulo enviado:
	 * Pasos: 
	 *  - Elimina el jnlp ppal (correspondiente al modulo, no eliminar jnlp adiccionales)
	 * @param module
	 * @throws DependencyViolationException
	 */
	/*private void uninstallModule(Module module) throws DependencyViolationException
	{
		String mensajeBase = getMessageResource("text.operacion.uninstall"); 
		try {
			this.userInterfaceFacade.notifyActivity(mensajeBase + ": " + getFullNameArtifact(module));
			File targetDirectory = new File(properties.getProperty(LOCALGIS_CLIENT_JNLP_DEPLOY_PATH));
			unDeployClientJnlp(targetDirectory, module);
			this.validUninstallation = true;
			showMessageSuccess(mensajeBase);
		}catch (Exception e) {
			showMessageFailed(mensajeBase);
			throw new RuntimeException(e);
		}
	}*/
	
	/**
	 * Copia fichero a instalar <clienteJnlp>.jar  en directorio que contiene libraries
	 * Extraer ficheros jnlp para despliegue
	 * @param targetDirectory
	 * @param targetLibraryDirectory
	 * @param binaryArtifact
	 */
	private void deployClientJars (File targetDirectory, File targetLibraryDirectory, Artifact binaryArtifact) {
		String mensajeBase = getMessageResource("text.proceso.desplegandoAplicacion");
		try {
			showMessageInitProcess(mensajeBase);
			//Obtener file jar a procesar
			File moduleJar = binaryArtifact.getFile();
			//Copiar <clienteJnlp>.jar si no existe
			File moduleTargetFile = new File(targetLibraryDirectory, moduleJar.getName());
			if(!moduleTargetFile.exists()) 
				FileUtils.copyFile(moduleJar, moduleTargetFile);
			//Procesar todos los ficheros jnlp: obtener lista nombres y extraer
			List lstFilesJnlp = getLstNameFilesJnlp(binaryArtifact);
			for (int i = 0; i < lstFilesJnlp.size(); i++) {
				//nameFileJnlp = getFullNameFileJnlp((String)lstFilesJnlp.get(i));
				UpdaterUtilities.extractJarFileToDirectory((String)lstFilesJnlp.get(i), moduleJar, targetDirectory);
			}
			
			try {
				if (libExtUpdater){
					List lstFilesJar= getLstNameFilesJar(binaryArtifact);
					if (lstFilesJar!=null){
						System.out.println("Dependencias a incluir en el servidor jetty en la carpeta  : " + targetLibraryDirectory+" "+lstFilesJar.size());
						for (int i = 0; i < lstFilesJar.size(); i++) {
							//nameFileJnlp = getFullNameFileJnlp((String)lstFilesJnlp.get(i));
							UpdaterUtilities.extractJarFileToDirectory((String)lstFilesJar.get(i), moduleJar, targetLibraryDirectory);
						}
					}
				}
			} catch (Exception e) {
			}
			
			showMessageSuccess(mensajeBase);
		} catch (IOException e) {
			showMessageFailed(mensajeBase);
			throw new RuntimeException(e);
		}catch (Exception e) {
			showMessageFailed(mensajeBase);
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * Elimina fichero <clienteJnlp>.jnlp existente
	 * @param targetDirectory
	 * @param module
	 */
	private void unDeployClientJnlp (File targetDirectory, Module module) {
		String nameClienteJnlp = "";
		String mensajeBase = getMessageResource("text.proceso.eliminandoDespliegueAplicacion");
		try {
			showMessageInitProcess(mensajeBase);
			//Obtener nombre cliente jnlp asociado al modulo
			nameClienteJnlp = getFileNameAppClientJnlp (module);
			//Borrar fichero JNLP
			File clientJnlp = new File(targetDirectory, nameClienteJnlp);
			if (clientJnlp.exists())
				clientJnlp.delete();
			showMessageSuccess(mensajeBase);
		} catch (Exception e) {
			showMessageFailed(mensajeBase);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Copia dependencias asociadas (/<dir_base_deploy>/<dir_libraries>)
	 * @param targetLibraryDirectory
	 * @param dependencies
	 */
	private void loadDependencies (File targetLibraryDirectory, Set<Artifact> dependencies) {
		int count = 0;
		File jarFileDependencies = null;
		File targetFileDependencies = null;
		String mensajeBase = getMessageResource("text.operacion.cargandoDependencias");
		try {
			showMessageInitProcess(mensajeBase);
			//Procesar dependencias: SOLO copiar elementos NO existentes
			for (Artifact dep : dependencies) {
				jarFileDependencies = dep.getFile();
				if (jarFileDependencies!=null){
					targetFileDependencies = new File(targetLibraryDirectory, jarFileDependencies.getName());
					//Cargamos si no existe
					if(!targetFileDependencies.exists()) {
						FileUtils.copyFile(jarFileDependencies, targetFileDependencies);
						count ++;
					}
				}
			}
			//Indicar numero dependencias cargadas
			showMessageOk(MessageFormat.format(getMessageResource("text.operacion.numDependenciasCargadas"), count));
		} catch (IOException e) {
			showMessageFailed(mensajeBase);
			throw new RuntimeException(e);
		} catch (Exception e) {
			showMessageFailed(mensajeBase);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Obtener nombre del fichero jnlp: nombre del modulo con extension jnlp
	 * @return
	 */
	private String getFileNameAppClientJnlp (Module module) {
		String nameAritifact = getFullFinalNameArtifact(module);
		//Obtener nombre del modulo sin extension para establecer .jnlp (y en ruta correspondiente)
		String nameFileJnlp = nameAritifact.substring(0, nameAritifact.lastIndexOf("."));
		nameFileJnlp = nameFileJnlp + ".jnlp";
		
		return nameFileJnlp;
	}
	
	/**
	 * Obtener lista de nombres jnlp existentes que debemos procesar nombre del fichero jnlp de despliegue
	 * @return
	 */
	private List getLstNameFilesJnlp (Artifact artifact) {
		List lstNames = null;
		try {
			JarEntry entry = null;
			lstNames = new ArrayList();
			//Procesar SOLO ruta con contenido jnlp para obtener lista de ficheros jnlp a procesar
			JarFile jarFile = new JarFile(artifact.getFile());
			Enumeration entries = jarFile.entries();
			while(entries.hasMoreElements()) {
				entry = (JarEntry) entries.nextElement();
				//No es directorio, empieza por nombre ruta jnlp y tiene extension final .jnlp
				if (!entry.isDirectory() && entry.getName().startsWith(JNLP_FILE_PATH) && entry.getName().endsWith(".jnlp"))
					lstNames.add(entry.getName());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return lstNames;
	}
	
	private List getLstNameFilesJar(Artifact artifact) {
		List lstNames = null;
		try {
			JarEntry entry = null;
			lstNames = new ArrayList();
			//Procesar SOLO ruta con contenido jnlp para obtener lista de ficheros jar a procesar
			JarFile jarFile = new JarFile(artifact.getFile());
			Enumeration entries = jarFile.entries();
			while(entries.hasMoreElements()) {
				entry = (JarEntry) entries.nextElement();
				//No es directorio, termina por extension jar
				if (!entry.isDirectory() && entry.getName().endsWith(".jar"))
					lstNames.add(entry.getName());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return lstNames;
	}
}
