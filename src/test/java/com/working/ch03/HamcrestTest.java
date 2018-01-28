package com.working.ch03;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class HamcrestTest {

	private List<String> mList;

	@Before
	public void setUpList() {
		mList = new ArrayList<String>();
		mList.add("x");
		mList.add("y");
		mList.add("z");
	}

	@Test
	public void testWithoutHamcrest() {
		assertTrue(mList.contains("one") || mList.contains("two") || mList.contains("three"));
	}

	@Test
	public void testWithHamcrest() {
		assertThat(mList, CoreMatchers.hasItem(CoreMatchers.anyOf(CoreMatchers.equalTo("one"),
				CoreMatchers.equalTo("two"), CoreMatchers.equalTo("three"))));
	}
}
