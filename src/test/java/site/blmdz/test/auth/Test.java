package site.blmdz.test.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.yaml.snakeyaml.Yaml;

import com.google.common.io.Resources;

public class Test {
	
	public static void main(String[] args) throws FileNotFoundException {
		Auths a = new Yaml().loadAs(new FileInputStream(new File(Resources.getResource("auth.yaml").getFile())), Auths.class);
		System.out.println(a);
	}
}
