package org.certificatic.spring.oxm.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class XMLConverter implements ResourceLoaderAware {

	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	private @Getter @Setter ResourceLoader resourceLoader;

	private static IInputStreamResourceReader isrr = (ir) -> {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(ir))) {
			return buffer.lines().collect(Collectors.joining("\n"));
		}
	};

	@FunctionalInterface
	public interface IInputStreamResourceReader {
		String read(InputStream input) throws IOException;
	}

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public void convertFromObjectToXML(Object object, String filepath)
			throws IOException {

		FileOutputStream os = null;
		try {
			os = new FileOutputStream(filepath);
			getMarshaller().marshal(object, new StreamResult(os));
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	public Object convertFromXMLToObject(String xmlfile) throws IOException {

		FileInputStream is = null;
		try {
			is = new FileInputStream(xmlfile);
			return getUnmarshaller().unmarshal(new StreamSource(is));
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	@SneakyThrows
	public String getXMLAsString(String location) {
		return XMLConverter.isrr.read(this.getResourceLoader().getResource(location).getInputStream());
	}

}