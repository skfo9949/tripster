package com.tripster.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tripster.domain.ContentsReviewVO;
import com.tripster.domain.ContentsVO;
import com.tripster.domain.Criteria;
import com.tripster.domain.PageMaker;
import com.tripster.service.ContentsReviewService;
import com.tripster.service.ContentsService;

@RestController
@RequestMapping("/contents/*")
public class ContentsController {
//	private static final Logger logger = LoggerFactory.getLogger(ContentsController.class);
	
	@Inject
	private ContentsService contentsService;
	@Inject
	private ContentsReviewService contentsReviewService;
	
		//맛집 리스트 조회 - REST 방식
		//ResponseEntity : JSON 객체  + HTTP 응답상태 전달, @Pathvariable로 변수를 받아서 사용
		@RequestMapping(value = "restaurantList/{curPage}", method = RequestMethod.GET)
		public ResponseEntity<Map<String, Object>> restaurantList(@PathVariable("curPage") Integer curPage) throws Exception {
			ResponseEntity<Map<String, Object>> entity = null;
			
			try {
				//컨텐츠 리스트 정보를 전달하기 위해, Criteria 객체 생성
				Criteria cri = new Criteria();
				cri.setCurPage(curPage);
				//페이지 정보를 전달하기 위해, PageMaker 객체 생성
				PageMaker pageMaker = new PageMaker();
				pageMaker.setCri(cri);
				
				//Map 객체 생성
				Map<String, Object> map = new HashMap<String, Object>();
				
				//ResponseEntity 객체에 담을 컨텐츠 객체 리스트 저장  
				List<ContentsVO> list = contentsService.getRestaurantList(cri);
				map.put("list", list);
				//ResponseEntity 객체에 담을 PageMaker 객체 저장
				pageMaker.setTotalCount(contentsService.getTotalRestaurantNum(cri));
				map.put("pageMaker", pageMaker);
				
				//View로 전달할 ResponsEntity 객체 생성 + 정보 전달 
				entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
				
			} catch(Exception e) {
				//오류 발생 시, BAD_REQUEST 상태 입력
				e.printStackTrace();
				entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return entity;
		}
		
		//맛집 리스트 조회 - REST 방식
		//ResponseEntity : JSON 객체  + HTTP 응답상태 전달, @Pathvariable로 변수를 받아서 사용
		@RequestMapping(value = "placeList/{curPage}", method = RequestMethod.GET)
		public ResponseEntity<Map<String, Object>> placeList(@PathVariable("curPage") Integer curPage) throws Exception {
			ResponseEntity<Map<String, Object>> entity = null;
			
			try {
				//컨텐츠 리스트 정보를 전달하기 위해, Criteria 객체 생성
				Criteria cri = new Criteria();
				cri.setCurPage(curPage);
				//페이지 정보를 전달하기 위해, PageMaker 객체 생성
				PageMaker pageMaker = new PageMaker();
				pageMaker.setCri(cri);
				
				//Map 객체 생성
				Map<String, Object> map = new HashMap<String, Object>();
				
				//ResponseEntity 객체에 담을 컨텐츠 객체 리스트 저장  
				List<ContentsVO> list = contentsService.getPlaceList(cri);
				map.put("list", list);
				//ResponseEntity 객체에 담을 PageMaker 객체 저장
				pageMaker.setTotalCount(contentsService.getTotalPlaceNum(cri));
				map.put("pageMaker", pageMaker);
				
				//View로 전달할 ResponsEntity 객체 생성 + 정보 전달 
				entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
				
			} catch(Exception e) {
				//오류 발생 시, BAD_REQUEST 상태 입력
				e.printStackTrace();
				entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return entity;
		}
		
		//컨텐츠 리스트 조회
//		@RequestMapping(value = "/contentsList", method = RequestMethod.GET)
//		public void contentsList(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
//			//View에 컨텐츠 리스트 전달
//			model.addAttribute("list", contentsService.getContentsList(cri));
//			//페이징 처리 객체 생성
//			PageMaker pageMaker = new PageMaker();
//			//페이징 처리를 위한 Criteria 객체 전달
//			pageMaker.setCri(cri);
//			//페이징 처리를 위한 총 컨텐츠 개수 전달 
//			pageMaker.setTotalCount(contentsService.getTotalContentsNum(cri));
//			//View에 페이징 처리 객체 전달
//			model.addAttribute("pageMaker", pageMaker);
//		}
		
		//컨텐츠 상세 정보 조회 - REST 방식
		//ResponseEntity : JSON + HTTP 응답상태까지 전달, @Pathvariable로 변수를 받아서 사용 
		@RequestMapping(value = "/restaurantDetail/{contentsID}", method = RequestMethod.GET)
		public ResponseEntity<ContentsVO> restaurantDetail(@PathVariable("contentsID") Integer contentsID) {
			ResponseEntity<ContentsVO> vo = null;
			
			try {
				//해당 contentsID를 가진 객체를 vo에 저장
				vo = new ResponseEntity<>(contentsService.getRestaurantDetail(contentsID), HttpStatus.OK);
				
			} catch(Exception e) {
				//오류 발생 시, BAD_REQUEST 상태 입력
				e.printStackTrace();
				vo = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return vo;
		}
		//컨텐츠 상세 정보 조회 - REST 방식
		//ResponseEntity : JSON + HTTP 응답상태까지 전달, @Pathvariable로 변수를 받아서 사용 
		@RequestMapping(value = "/placeDetail/{contentsID}", method = RequestMethod.GET)
		public ResponseEntity<ContentsVO> placeDetail(@PathVariable("contentsID") Integer contentsID) {
			ResponseEntity<ContentsVO> vo = null;
			
			try {
				//해당 contentsID를 가진 객체를 vo에 저장
				vo = new ResponseEntity<>(contentsService.getPlaceDetail(contentsID), HttpStatus.OK);
				
			} catch(Exception e) {
				//오류 발생 시, BAD_REQUEST 상태 입력
				e.printStackTrace();
				vo = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return vo;
		}
		
		//컨텐츠 상세 정보 조회 - 구글맵 연동 테스트용 페이지
		@RequestMapping(value = "/contentsDetail/{contentsID}/{location}", method = RequestMethod.GET)
		public void contentsDetail() throws Exception {
//			ContentsVO vo = null;
//			try {
//				//해당 contentsID를 가진 객체를 vo에 저장
//				vo = contentsService.getContentsDetail(2);
//				System.out.println(vo.toString());
//				System.out.println("hi hello");
//			} catch(Exception e) {
//				//오류 발생 시, BAD_REQUEST 상태 입력
//				e.printStackTrace();
//			}
//			return vo;
		}
	
		//맛집 상세 페이지에서 리뷰 리스트 조회
		//@PathVariable로 변수를 받아서 사용
		@RequestMapping(value = "/contentsDetail/{contentsID}/{curPage}", method = RequestMethod.GET)
		public ResponseEntity<Map<String, Object>> contentsReviewList(
				@PathVariable("contentsID") Integer contentsID,
				@PathVariable("curPage") Integer curPage) {
			ResponseEntity<Map<String, Object>> entity = null;
				
			try {
				//댓글 리스트 정보를 전달하기 위해, Criteria 객체 생성
				Criteria cri = new Criteria();
				//현재 댓글 페이지 정보 저장
				cri.setCurPage(curPage);
				
				//페이지 정보를 전달하기 위해, PageMaker 객체 생성
				PageMaker pageMaker = new PageMaker();
				pageMaker.setCri(cri);
				
				//Map 객체 저장
				Map<String, Object> map = new HashMap<String, Object>();
				//ResponsEntity 객체에 담을 댓글 리스트 정보 저장
				List<ContentsReviewVO> list = contentsReviewService.getReviewList(contentsID, cri);
				map.put("list", list);
				
				//ResponsEntity 객체에 담을 페이지 정보 저장
				int reviewCount = contentsReviewService.getTotalReviewNum(contentsID);
				pageMaker.setTotalCount(reviewCount);
				map.put("pageMaker", pageMaker);
				
				//View로 전달할 ResponsEntity 객체 생성 + 정보 전달 
				entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
				
			} catch(Exception e) {
				e.printStackTrace();
				//오류 발생 시, BAR_REQUEST 상태 입력
				entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
			}
			
			return entity;
		}
	
		//컨텐츠 리뷰 작성
		//@RequestBody : View에서 JSON 객체를 전달 받아 사용
		@RequestMapping(value="/contentsDetail/{contentsID}", method = RequestMethod.POST)
		public ResponseEntity<String> writeReview(@PathVariable Integer contentsID, @RequestBody ContentsReviewVO vo) {
			//ResponseEntity : View로 JSON + HTTP 상태 전달
			ResponseEntity<String> entity = null;
			
			try {
				//PathVariable 활용, 해당 맛집의 리뷰 저장
				vo.setContentsID(contentsID);
				contentsReviewService.writeReview(vo);
				//View로 전달할 ResponsEntity 객체 생성 + 정보 전달
				entity = new ResponseEntity<String>("written", HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				//오류 발생 시, BAR_REQUEST 상태 입력
				entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
			
			return entity;
		}
		
		//컨텐츠 리뷰 수정
		@RequestMapping(value="/contentsDetail/{contentsID}/{contentsReviewID}",
				method = { RequestMethod.PUT, RequestMethod.PATCH })
		public ResponseEntity<String> modifyReview(@PathVariable("contentsID") Integer contentsID,
											 @PathVariable("contentsReviewID") Integer contentsReviewID,
											 @RequestBody ContentsReviewVO vo) {

			ResponseEntity<String> entity = null;
			
			try {
				//PathVariable 활용, 해당 컨텐츠의 리뷰 수정 사항 저장
				vo.setContentsReviewID(contentsReviewID);
				contentsReviewService.modifyReview(vo);
				
				entity = new ResponseEntity<String>("modified",HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				//오류 발생 시, BAR_REQUEST 상태 입력
				entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			
			return entity;
		}
		
		//컨텐츠 리뷰 삭제
		@RequestMapping(value="/contentsDetail/{contentsID}/{contentsReviewID}", method = RequestMethod.DELETE )
		public ResponseEntity<String> deleteReview(@PathVariable("contentsID") Integer contentsID,
											 @PathVariable("contentsReviewID") Integer contentsReviewID) {
			ResponseEntity<String> entity = null;
			
			//PathVariable 활용, 컨텐츠 리뷰 삭제
			try {
				contentsReviewService.deleteReview(contentsReviewID);
				entity = new ResponseEntity<String>("deleted",HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
				//오류 발생 시, BAR_REQUEST 상태 입력
				entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			
			return entity;
		}
}