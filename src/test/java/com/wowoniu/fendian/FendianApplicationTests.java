package com.wowoniu.fendian;

import com.wowoniu.fendian.mapper.MemberStatisticMapper;
import com.wowoniu.fendian.mapper.UseUserMapper;
import com.wowoniu.fendian.mapper.UserLoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FendianApplicationTests {
	@Autowired
	UserLoginMapper userLoginMapper;
	@Autowired
	UseUserMapper useUserMapper;
	@Autowired
	MemberStatisticMapper memberStatisticMapper;
	@Test
	void contextLoads() {
		useUserMapper.selectByPrimaryKey("1");
	}

}
