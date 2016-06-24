package com.ourpalm.editor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.sshtools.net.SocketTransport;
import com.sshtools.publickey.InvalidPassphraseException;
import com.sshtools.publickey.SshPrivateKeyFile;
import com.sshtools.publickey.SshPrivateKeyFileFactory;
import com.sshtools.ssh.HostKeyVerification;
import com.sshtools.ssh.PasswordAuthentication;
import com.sshtools.ssh.PublicKeyAuthentication;
import com.sshtools.ssh.SshAuthentication;
import com.sshtools.ssh.SshClient;
import com.sshtools.ssh.SshConnector;
import com.sshtools.ssh.SshContext;
import com.sshtools.ssh.SshException;
import com.sshtools.ssh.SshSession;
import com.sshtools.ssh.components.SshKeyPair;
import com.sshtools.ssh.components.SshPublicKey;

public class MaverickShellTool {
	private SshConnector conn;
	private String ipAddr;
	private static String charset = Charset.forName("UTF-8").toString();
	private String userName;
	private String password;
	private SshClient ssh;
	private SshSession session;
	private File keyPath;
	private int type;// 1密码 2key

	public MaverickShellTool(String ipAddr, String userName, String password) {
		this.ipAddr = ipAddr;
		this.userName = userName;
		this.password = password;
		this.type = 1;
	}

	public MaverickShellTool(File keyPath, String ipAddr, String userName) {
		this.keyPath = keyPath;
		this.ipAddr = ipAddr;
		this.userName = userName;
		this.type = 2;
	}

	public boolean login() throws SshException, IOException {
		conn = SshConnector.createInstance();
		System.out.println("准备连接");
		ssh = conn.connect(new SocketTransport(ipAddr, 22), userName);
		PasswordAuthentication pwd = new PasswordAuthentication();
		pwd.setPassword(password);
		do {
			pwd.setPassword(password);
		} while (ssh.authenticate(pwd) != SshAuthentication.COMPLETE && ssh.isConnected());

		return ssh.isAuthenticated(); // 认证
	}

	public boolean loginByKey() throws SshException, IOException, InvalidPassphraseException {
		conn = SshConnector.createInstance();
		System.out.println("准备连接");
		HostKeyVerification hkv = new HostKeyVerification() {
			public boolean verifyHost(String hostname, SshPublicKey key) {
				try {
					System.out.println("The connected host's key (" + key.getAlgorithm() + ") is");
					System.out.println(key.getFingerprint());
				} catch (SshException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		};

		SshContext context = conn.getContext();
		context.setHostKeyVerification(hkv);

		ssh = conn.connect(new SocketTransport(ipAddr, 22), userName);

		PublicKeyAuthentication pk = new PublicKeyAuthentication();

		SshPrivateKeyFile pkfile = SshPrivateKeyFileFactory.parse(new FileInputStream(keyPath));
		SshKeyPair keyPair = pkfile.toKeyPair(null);

		pk.setPrivateKey(keyPair.getPrivateKey());
		pk.setPublicKey(keyPair.getPublicKey());
		while (ssh.authenticate(pk) != SshAuthentication.COMPLETE && ssh.isConnected())
			;

		return ssh.isAuthenticated(); // 认证
	}

	public String exec(String cmds) throws Exception {
		StringBuffer result = new StringBuffer();
		InputStream in = null;
		try {
			boolean flag = false;
			if (type == 1) {
				flag = login();
			} else if (type == 2) {
				flag = loginByKey();
			}
			if (flag) {
				System.out.println("认证通过");
				session = ssh.openSessionChannel();
				session.startShell();

				session = ssh.openSessionChannel();

				if (session.requestPseudoTerminal("vt100", 80, 24, 0, 0)) {
					// session.setAutoConsumeInput(true);
					session.executeCommand(cmds);
					in = session.getInputStream();
					BufferedReader stderr = new BufferedReader(new InputStreamReader(in, charset));
					while (true) {
						String line = stderr.readLine();
						if (line == null) {
							break;
						} else {
							result.append(line).append("\n");
						}
					}
				} else
					System.out.println("Failed to allocate pseudo terminal");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
			IOUtils.closeQuietly(in);
		}
		return result.toString();
	}

	public static String execLocalShell(String cmd) {
		StringBuffer result = new StringBuffer();
		InputStream in = null;
		try {
			String[] shell = { "sh", "-c", cmd };
			Process process = Runtime.getRuntime().exec(shell);
			if (process != null) {
				in = process.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, charset));
				process.waitFor();
				while (true) {
					String line = bufferedReader.readLine();
					if (line == null) {
						break;
					} else {
						result.append(line).append("\n");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sh cmd not found...");
		} finally {
			IOUtils.closeQuietly(in);
		}
		return result.toString();
	}

	public static void main(String args[]) throws Exception {
		try {

			// Long start = System.currentTimeMillis();
			// MaverickShellTool rt = new MaverickShellTool("192.168.1.71",
			// "root", "gamebean");
			// String cmds =
			// "cd /home/root/ssol/tools/script ; ./useItem.sh 2012-04-05
			// 1590334 MP7 灵力";
			// String cmds = "sh /root/LoginServer/update.sh";
			// String cmds = "sh /root/testServer/tianjiang/update.sh";
			// String exec = rt.exec(cmds1);
			// System.out.println(exec);
			// String exec1 = rt.exec(cmds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
