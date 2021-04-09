package com.code5.fw.db;

import java.util.List;

// 일종의 VO
// 개발을 편하게 하기위해 get, set미구현
// Get, Set메소드로 캡슐화 한다고해서, 어차피 멤버가 안전하지는 않다
// VO를 작성할 때에는 이후 변경영역을 최소화 하는 방향으로 작성해야한다. 
public class SqlRunnerB {
	String key = null;
	String sqlOrg = null;
	String sql =null;
	List<String> param = null;
}
