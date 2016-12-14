package com.liquidforte.scheduler.entity;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class User {
	private static final int ITERATIONS = 64;
	private static final int KEY_LENGTH = 256;

	private String name;
	private String password;
	private String passwordHash;
	private String passwordSalt;
	private Set<String> groups;
	private Set<String> events;

	public User() {

	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordHash() {
		if (passwordHash == null && password != null && !password.isEmpty()) {
			try {
				SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
				PBEKeySpec spec = new PBEKeySpec(getPassword().toCharArray(), getPasswordSalt().getBytes(), ITERATIONS,
						KEY_LENGTH);
				SecretKey key = skf.generateSecret(spec);
				byte[] res = key.getEncoded();
				setPasswordHash(new String(res));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}
		}
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordSalt() {
		if (passwordSalt == null || passwordSalt.isEmpty()) {
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			setPasswordSalt(new String(salt));
		}
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public Set<String> getGroups() {
		return groups;
	}

	public void setGroups(Set<String> groups) {
		this.groups = groups;
	}

	public Set<String> getEvents() {
		return events;
	}

	public void setEvents(Set<String> events) {
		this.events = events;
	}
}
