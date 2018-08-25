package cn.qlq.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Namespace("/test")
@ParentPackage("json-default")
public class TestAction {
	private String jsonStr;

	private static Logger log = LoggerFactory.getLogger(TestAction.class);

	@Action(value = "testEnv", results = { @Result(name = "json", type = "json") })
	public String test1() {
		this.jsonStr = "测试环境";
		log.debug("test logger!!!");
		return "json";
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
}
