package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import objects.Config;

public class ConfigReader {
	
	List<Config> configs = new ArrayList<Config>();

	public void read(String configPath) {
		Document doc = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(configPath);
			Element mural = doc.getRootElement();
			List<?> elements = mural.getChildren();
			Iterator<?> i = elements.iterator();
			
			while (i.hasNext()) {
				Element element = (Element) i.next();
				configs.add(new Config(element.getChildText("name"), Boolean.valueOf(element.getChildText("active")),
					Integer.valueOf(element.getChildText("order")), Boolean.valueOf(element.getChildText("descending"))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Config> getConfigs() {
		Collections.sort(configs);
		return configs;
	}
}
