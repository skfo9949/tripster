package com.tripster.service;

import java.util.HashMap;
import java.util.List;

import com.tripster.domain.EsContentsVO;
import com.tripster.domain.EsMemberVO;
import com.tripster.domain.EsPlanVO;
import com.tripster.domain.EsSearchResult;
import com.tripster.domain.SearchCriteria;


public interface EsSearchService {

	// 통합검색결과 리스트 조회 
	public EsSearchResult getTotalSearchList(SearchCriteria cri) throws Exception;
	// 통합검색결과 건수 조회 
	public HashMap<String,String> getTotalSearchNum(SearchCriteria cri) throws Exception;	
	
}
