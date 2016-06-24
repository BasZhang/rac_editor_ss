package com.ourpalm.editor.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.Charset;

import com.alibaba.fastjson.util.IOUtils;
import com.ourpalm.editor.TableEntity;

public class FileUtils {

	private FileUtils() {
	}

	/**
	 * Make or override a file with full path of <code>filePath</code> and that
	 * its content written by <code>content</code>.
	 * 
	 * @param filePath
	 *            where the file should be written
	 * @param content
	 *            the file's content
	 * @throws IOException 
	 */
	public static void makeFile(String filePath, String content) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			File parentFolder = new File(file.getParent());
			parentFolder.mkdirs();
			file.createNewFile();
		}
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			os.write(content.getBytes(Charset.forName("UTF-8")));
		} finally {
			IOUtils.close(os);
		}
	}

	/**
	 * 根据类所在包在资源根包{@link EntityUtils#PACKAGE}下的相对位置，对应出在资源目录下的文件路径。
	 * <p>
	 * 例如：<br>
	 * 若，资源根包为 <code>"com.ourpalm.editor.entity"</code><br>
	 * 输出目录根文件夹为 <code>"project/res/"</code><br>
	 * 输出文件名为 "类名.txt"，<br>
	 * 则，类 <code>com.ourpalm.editor.entity.conf.ArrayRule</code><br>
	 * 对应出的文件路径为 <code>"project/res/conf/ArrayRule.txt"</code>。
	 * 
	 * @param fileName
	 *            输出指定文件名
	 * @param tableCls
	 *            资源类
	 * @param toPathRoot
	 *            输出文件根目录
	 * @return 资源目录下的文件路径。
	 * @throws NullPointerException 当参数为<code>null</code>。
	 */
	public static String migrateResPath(String fileName, Class<? extends TableEntity> tableCls, String toPathRoot) {
		String SEPARATOR = File.separator;
		String packageCanonicalName = org.apache.commons.lang3.ClassUtils.getPackageCanonicalName(tableCls);
		String subPackage = packageCanonicalName.substring(EntityUtils.PACKAGE.length());
		String subFolder = subPackage.length() > 0 ? subPackage.substring(1).replace(".", SEPARATOR) : "";
		StringBuilder sb = new StringBuilder(toPathRoot);
		if (!toPathRoot.endsWith("\\") && !toPathRoot.endsWith("/"))
			sb.append(SEPARATOR);
		if (!subFolder.equals("")) {
			sb.append(subFolder);
			sb.append(SEPARATOR);
		}
		sb.append(fileName);
		return sb.toString();
	}

	public static String formatSize(long size) {
		double kiloByte = size / 1024;
		if (kiloByte < 1) {
			return size + "Byte(s)";
		}

		double megaByte = kiloByte / 1024;
		if (megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
		}

		double gigaByte = megaByte / 1024;
		if (gigaByte < 1) {
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
		}

		double teraBytes = gigaByte / 1024;
		if (teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
	}

//	/**
//	 * 根据tianjiang服务器框架中的资源模块，用另一个tianjiang项目的字节码目录中class文件，
//	 * 生成此编辑器要用到的TableEntity子类Java文件。
//	 * <p>
//	 * 
//	 * @param classPath
//	 *            tianjiang的classPath
//	 * @param baseResPackage
//	 *            tianjiang的资源模根包
//	 * @param destPath
//	 *            输出目录
//	 * @param baseToPackage
//	 *            输出Java文件的根包
//	 * @throws IOException
//	 * 
//	 * @sinse java 1.7
//	 */
//	public static void generateJavaFiles(final Path classPath, final String baseResPackage, final Path destPath, final String baseToPackage) throws IOException {
//		final String classPathStr = classPath.toString();
//		final MyFileClassLoader fileClsLoader = new MyFileClassLoader();
//		fileClsLoader.setClassPath(classPathStr);
//		Files.walkFileTree(classPath, new FileVisitor<Path>() {
//			private Path crtFromDir;
//			private Path crtToDir = destPath;
//			{
//				if (Files.notExists(destPath)) {
//					Files.createDirectory(destPath);
//					System.out.println("新建输出根目录" + destPath);
//				}
//			}
//			/**
//			 * <code>com.ourpalm.editor.entity</code>之后的东西。
//			 */
//			private String subPackage = "";
//
//			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//				crtFromDir = dir.getParent();
//				if (!subPackage.equals("")) {
//					crtToDir = crtToDir.getParent();
//				}
//				int lastDotIndexOf = subPackage.lastIndexOf('.');
//				if (lastDotIndexOf != -1) {
//					subPackage = subPackage.substring(0, subPackage.lastIndexOf('.'));
//				}
//				return FileVisitResult.CONTINUE;
//			}
//
//			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//				crtFromDir = dir;
//				String fromPackage = crtFromDir.toString().substring(classPathStr.length()).replace('\\', '.');
//				if (fromPackage.startsWith(".")) {
//					fromPackage = fromPackage.substring(1);
//				}
//				if (baseResPackage.contains(fromPackage) && !fromPackage.contains(baseResPackage)) { // 资源包的上层
//					return FileVisitResult.CONTINUE;
//				}
//				if (fromPackage.contains(baseResPackage)) { // 资源包本身或其子包
//					if (!baseResPackage.contains(fromPackage) && fromPackage.contains(baseResPackage)) {// 资源包的子包
//						subPackage += "." + dir.getFileName().toString();
//					}
//					if (!subPackage.equals("")) {
//						String lastSubFolder = subPackage.substring(subPackage.lastIndexOf('.') + 1);
//						crtToDir = Paths.get(crtToDir.toString() + "\\" + lastSubFolder);
//					}
//					if (!Files.exists(crtToDir))
//						Files.createDirectory(crtToDir);
//					return FileVisitResult.CONTINUE;
//				}
//				return FileVisitResult.SKIP_SUBTREE; // 误入歧途的包直接跳出
//			}
//
//			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//				String absFileName = crtFromDir.toString() + "\\" + file.getFileName().toString();
//				if (absFileName.endsWith(".class")) {
//					String className = absFileName.substring(classPathStr.length() + 1, absFileName.length() - 6).replace("\\", ".");
//					if (className.startsWith(baseResPackage)) { // 在那个资源包内
//						try {
//							Class<?> cls = fileClsLoader.loadClass(className);
//							if (!cls.isInterface()) {
//								Path newPath = Paths.get(crtToDir.toString() + "\\" + cls.getSimpleName() + ".java");
//								Files.deleteIfExists(newPath);
//								System.out.println("新建文件" + newPath);
//								Path newFile = Files.createFile(newPath);
//
//								writeContent(cls, newFile);
//							}
//						} catch (ClassNotFoundException e) {
//							System.out.printf("FileClassLoader load class %s fail", className);
//							e.printStackTrace();
//						}
//					}
//
//				}
//				return FileVisitResult.CONTINUE;
//			}
//
//			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
//				return FileVisitResult.CONTINUE;
//			}
//
//			private void writeContent(Class<?> cls, Path toFile) {
//				String shortClassName = cls.getSimpleName();
//				boolean arrayExists = false;
//				Map<String, Class<?>> fieldInfo = Maps.newHashMap();
//				for (Field field : cls.getDeclaredFields()) {
//					Class<?> type = field.getType();
//					if (!Modifier.isStatic(field.getModifiers())) {
//						fieldInfo.put(field.getName(), type);
//						if (type.isArray()) {
//							arrayExists = true;
//						}
//					}
//				}
//				// 写文件
//				File file = toFile.toFile();
//				PrintWriter writer = null;
//				try {
//					writer = new PrintWriter(file);
//					writer.write("package ");
//					writer.write(baseToPackage);
//					writer.write(subPackage);
//					writer.write(";\n");
//					writer.write("\n");
//					writer.write("import javax.persistence.Column;\n");
//					writer.write("import javax.persistence.Entity;\n");
//					writer.write("import javax.persistence.GeneratedValue;\n");
//					writer.write("import javax.persistence.GenerationType;\n");
//					writer.write("import javax.persistence.Id;\n");
//					writer.write("import javax.persistence.Table;\n");
//					writer.write("\n");
//					writer.write("import com.ourpalm.editor.TableEntity;\n");
//					if (arrayExists) {
//						writer.write("import com.ourpalm.editor.annotation.ArrayData;\n");
//					}
//					writer.write("import com.ourpalm.editor.annotation.Comment;\n");
//					writer.write("\n");
//					writer.write("@Entity\n");
//					writer.write("@Comment(desc = \"");
//					writer.write(shortClassName);
//					writer.write("\")\n");
//					writer.write("@Table(name = \"");
//					writer.write(shortClassName);
//					writer.write("\")\n");
//					writer.write("public class ");
//					writer.write(shortClassName);
//					writer.write(" implements TableEntity {\n\n");
//					writer.write("\t@Id\n");
//					writer.write("\t@Comment(search = \"search_EQ_code\", desc = \"id\")\n");
//					writer.write("\t@Column(name = \"code\")\n");
//					writer.write("\t@GeneratedValue(strategy = GenerationType.AUTO)\n");
//					writer.write("\tprivate Long code;\n\n");
//					for (Entry<String, Class<?>> entry : fieldInfo.entrySet()) {
//						String fieldName = entry.getKey();
//						Class<?> fieldType = entry.getValue();
//						String typeName = fieldType.getSimpleName();
//						writer.write("\t@Comment(search = \"search_");
//						if (fieldType.isArray()) {
//							typeName = "String";
//						}
//						if (fieldType == String.class) {
//							writer.write("LIKE_");
//						} else {
//							writer.write("EQ_");
//						}
//						writer.write(fieldName);
//						writer.write("\", desc = \"");
//						writer.write(fieldName);
//						writer.write("\")\n");
//						writer.write("\t@Column(name = \"");
//						writer.write(fieldName);
//						writer.write("\", nullable = ");
//						if (typeName.equals("String")) {
//							writer.write("true");
//						} else {
//							writer.write("false");
//						}
//						writer.write(")\n");
//						if (fieldType.isArray()) {
//							writer.write("\t@ArrayData\n");
//						}
//						writer.write("\tprivate ");
//						writer.write(typeName);
//						writer.write(" ");
//						writer.write(fieldName);
//						writer.write(";\n\n");
//					}
//					writer.write("\t@Comment(search = \"search_EQ_isUpdate\", desc = \"是否更新\")\n");
//					writer.write("\t@Column(name = \"isUpdate\", nullable = false)\n");
//					writer.write("\tprivate int isUpdate;\n");
//					writer.write("\n");
//					writer.write("\t@Override\n");
//					writer.write("\tpublic Long getCode() {\n");
//					writer.write("\t\treturn code;\n");
//					writer.write("\t}\n\n");
//					writer.write("\t@Override\n");
//					writer.write("\tpublic void setCode(Long code) {\n");
//					writer.write("\t\tthis.code = code;\n");
//					writer.write("\t}\n\n");
//
//					for (Entry<String, Class<?>> entry : fieldInfo.entrySet()) {
//						String fieldName = entry.getKey();
//						Class<?> fieldType = entry.getValue();
//						String typeName = fieldType.getSimpleName();
//						if (fieldType.isArray()) {
//							typeName = "String";
//						}
//						writer.write("\tpublic ");
//						writer.write(typeName);
//						writer.write(" get");
//						writer.write(StringUtils.capitalize(fieldName));
//						writer.write("() {\n");
//						writer.write("\t\treturn ");
//						writer.write(fieldName);
//						writer.write(";\n");
//						writer.write("\t}\n\n");
//
//						writer.write("\tpublic void set");
//						writer.write(StringUtils.capitalize(fieldName));
//						writer.write("(");
//						writer.write(typeName);
//						writer.write(" ");
//						writer.write(fieldName);
//						writer.write(") {\n");
//						writer.write("\t\tthis.");
//						writer.write(fieldName);
//						writer.write(" = ");
//						writer.write(fieldName);
//						writer.write(";\n");
//						writer.write("\t}\n\n");
//					}
//
//					writer.write("\tpublic int getIsUpdate() {\n");
//					writer.write("\t\treturn isUpdate;\n");
//					writer.write("\t}\n\n");
//					writer.write("\tpublic void setIsUpdate(int isUpdate) {\n");
//					writer.write("\t\tthis.isUpdate = isUpdate;\n");
//					writer.write("\t}\n\n");
//					writer.write("}");
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} finally {
//					IOUtils.close(writer);
//				}
//			}
//		});
//	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		try {
//			FileUtils.generateJavaFiles(Paths.get("D:\\program_folder\\racing\\WEB-INF\\classes\\"), "com.ourpalm.server.res", Paths.get("F:\\baiduyundownload\\res"), "com.ourpalm.editor.entity");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}