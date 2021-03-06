/**   
 * Copyright © 2015 fenlibao.com. All rights reserved.
 * 
 * @Title: InviteController.java 
 * @Prject: client-api-fenlibao
 * @Package: com.fenlibao.p2p.controller.v_1.v_1_3_0.invite 
 * @Description: TODO
 * @author: laubrence  
 * @date: 2015-11-10 下午8:54:13 
 * @version: V1.1   
 */
package com.fenlibao.p2p.controller.v_1.v_1_0_0.mp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenlibao.p2p.model.entity.UserAccount;
import com.fenlibao.p2p.model.global.APIVersion;
import com.fenlibao.p2p.model.global.BaseRequestForm;
import com.fenlibao.p2p.model.global.HttpResponse;
import com.fenlibao.p2p.model.global.InterfaceConst;
import com.fenlibao.p2p.model.global.ResponseCode;
import com.fenlibao.p2p.model.mp.entity.MyPoint;
import com.fenlibao.p2p.model.mp.entity.UserPointDetail;
import com.fenlibao.p2p.model.mp.vo.MyPointVO;
import com.fenlibao.p2p.model.mp.vo.UserPointDetailVO;
import com.fenlibao.p2p.service.UserInfoService;
import com.fenlibao.p2p.service.mp.MemberPointsService;
import com.fenlibao.p2p.service.privateMessage.PrivateMessageService;
import com.fenlibao.p2p.util.CommonTool;
import com.fenlibao.p2p.util.DateUtil;
import com.fenlibao.p2p.util.loader.Sender;

/** 
 * @ClassName: InviteController 
 * @Description: TODO
 * @author: laubrence
 * @date: 2016-2-6 01:10:59
 */

@RestController("v_1_0_0/MemberPointsController")
@RequestMapping("mp")
public class MemberPointsController {

	private static final Logger logger= LogManager.getLogger(MemberPointsController.class);
	
	@Resource
	MemberPointsService memberPointsService;
	
	@Resource
	PrivateMessageService privateMessageService;
	
	@Resource
	UserInfoService userInfoService;
	
	/**
	 * @title 查询当前用户积分
	 * @param request
	 * @param paramForm
	 * @param token
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/points/info", method = RequestMethod.GET, headers = APIVersion.V_1_0_0)
    HttpResponse getInviteInfo(HttpServletRequest request,
    		@ModelAttribute BaseRequestForm  paramForm,
    		@RequestParam(required = true, value = "token") String token,
    		@RequestParam(required = true, value = "userId") String userId) throws Exception{
		HttpResponse response = new HttpResponse();
		try{
			if(!paramForm.validate() || StringUtils.isEmpty(token) || StringUtils.isEmpty(userId)){
				response.setCodeMessage(ResponseCode.EMPTY_PARAM.getCode(),ResponseCode.EMPTY_PARAM.getMessage());
				return response;
			}
			MyPointVO myPointVO = new MyPointVO();
			MyPoint myPoint = memberPointsService.getMyPointsNum(Integer.valueOf(userId));
			if(myPoint != null ){
				myPointVO.setPointNum(myPoint.getPointNum());
				response.setData(CommonTool.toMap(myPointVO));
			}
			return response;
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * @title 用户最近30天积分使用记录查询
	 * @param request
	 * @param paramForm
	 * @param token
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "points/consume/records/thirty", method = RequestMethod.GET, headers = APIVersion.V_1_0_0)
    HttpResponse getPointRecordsThirty(HttpServletRequest request,
    		@ModelAttribute BaseRequestForm  paramForm,
    		@RequestParam(required = true, value = "token") String token,
    		@RequestParam(required = true, value = "userId") String userId) throws Exception{
		HttpResponse response = new HttpResponse();
		try{
			if(!paramForm.validate() || StringUtils.isEmpty(token) || StringUtils.isEmpty(userId)){
				response.setCodeMessage(ResponseCode.EMPTY_PARAM.getCode(),ResponseCode.EMPTY_PARAM.getMessage());
				return response;
			}
			List<UserPointDetailVO> userPointVOList= new ArrayList<UserPointDetailVO>();
			List<UserPointDetail> userPointList = memberPointsService.getPointRecordsThirty(Integer.valueOf(userId));
			if(userPointList != null && userPointList.size() > 0 ){
				for(UserPointDetail usrePoint : userPointList){
					UserPointDetailVO userPointVO = new UserPointDetailVO();
					userPointVO.setpName(usrePoint.getpName());
					userPointVO.setpLogo(usrePoint.getpLogo());
					userPointVO.setpChangeType(usrePoint.getpChangeType());
					userPointVO.setpNum(usrePoint.getpNum());
					userPointVO.setCreateTime(usrePoint.getCreateTime().getTime()/1000);
					
					userPointVOList.add(userPointVO);
				}
			}
			response.getData().put("pRecordsList",userPointVOList);
			return response;
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * @title 积分使用记录查询(全部/收入/支出)
	 * @param request
	 * @param paramForm
	 * @param token
	 * @param userId
	 * @param changeType
	 * @param createTime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "points/consume/records", method = RequestMethod.GET, headers = APIVersion.V_1_0_0)
    HttpResponse getPointRecords(HttpServletRequest request,
    		@ModelAttribute BaseRequestForm  paramForm,
    		@RequestParam(required = true, value = "token") String token,
    		@RequestParam(required = true, value = "userId") String userId,
    		@RequestParam(required = true, value = "changeType") String changeType,
    		@RequestParam(required = false, value = "createTime") String createTime) throws Exception{
		HttpResponse response = new HttpResponse();
		try{
			if(!paramForm.validate() || StringUtils.isEmpty(token) || StringUtils.isEmpty(userId)){
				response.setCodeMessage(ResponseCode.EMPTY_PARAM.getCode(),ResponseCode.EMPTY_PARAM.getMessage());
				return response;
			}
			List<UserPointDetailVO> userPointVOList= new ArrayList<UserPointDetailVO>();
			List<UserPointDetail> userPointList = memberPointsService.getPointRecords(Integer.valueOf(userId),Integer.valueOf(changeType),createTime);
			if(userPointList != null && userPointList.size() > 0 ){
				for(UserPointDetail usrePoint : userPointList){
					UserPointDetailVO userPointVO = new UserPointDetailVO();
					userPointVO.setpName(usrePoint.getpName());
					userPointVO.setpLogo(usrePoint.getpLogo());
					userPointVO.setpChangeType(usrePoint.getpChangeType());
					userPointVO.setpNum(usrePoint.getpNum());
					userPointVO.setCreateTime(usrePoint.getCreateTime().getTime()/1000);
					
					userPointVOList.add(userPointVO);
				}
				response.getData().put("pRecordsList",userPointVOList);
			}
			return response;
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * @title 根据积分计算抵扣金额接口（手机充值/现金兑换）
	 * @param request
	 * @param paramForm
	 * @param token
	 * @param userId
	 * @param pTypeCode
	 * @param pNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "points/exchange/cash/amount", method = RequestMethod.GET, headers = APIVersion.V_1_0_0)
    HttpResponse pointsExchangeCashAmount(HttpServletRequest request,
    		@ModelAttribute BaseRequestForm  paramForm,
    		@RequestParam(required = true, value = "token") String token,
    		@RequestParam(required = true, value = "userId") String userId,
    		@RequestParam(required = true, value = "pTypeCode") String pTypeCode,
    		@RequestParam(required = true, value = "pNum") String pNum) throws Exception{
		HttpResponse response = new HttpResponse();
		try{
			if(!paramForm.validate() || StringUtils.isEmpty(token) || StringUtils.isEmpty(userId)){
				response.setCodeMessage(ResponseCode.EMPTY_PARAM.getCode(),ResponseCode.EMPTY_PARAM.getMessage());
				return response;
			}
			BigDecimal cashAmount = memberPointsService.getPointsExchangeCashAmount(pTypeCode,Integer.valueOf(pNum));
			response.getData().put("cashAmount",cashAmount);
			return response;
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * @title 积分兑换现金 
	 * @param request
	 * @param paramForm
	 * @param token
	 * @param userId
	 * @param pTypeCode
	 * @param pNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "points/exchange/cash", method = RequestMethod.POST, headers = APIVersion.V_1_0_0)
    HttpResponse pointsExchangeCash(HttpServletRequest request,
    		@ModelAttribute BaseRequestForm  paramForm,
    		@RequestParam(required = true, value = "token") String token,
    		@RequestParam(required = true, value = "userId") String userId,
    		@RequestParam(required = true, value = "pTypeCode") String pTypeCode,
    		@RequestParam(required = true, value = "pNum") String pNum) throws Exception{
		HttpResponse response = new HttpResponse();
		try{
			if(!paramForm.validate() || StringUtils.isEmpty(token) || StringUtils.isEmpty(userId)){
				response.setCodeMessage(ResponseCode.EMPTY_PARAM.getCode(),ResponseCode.EMPTY_PARAM.getMessage());
				return response;
			}
			//积分兑换现金
			Map<String,Object> returnMap = memberPointsService.pointsExchangeCash(Integer.valueOf(userId),pTypeCode,Integer.valueOf(pNum));
			int returnpNum = returnMap.get("pNum")!=null?Integer.valueOf(String.valueOf(returnMap.get("pNum"))):0;
			BigDecimal cashAmount = returnMap.get("cashAmount")!=null? new BigDecimal((String.valueOf(returnMap.get("cashAmount")))):new BigDecimal(0.00);
			BigDecimal balance = new BigDecimal(0.00);
			String accountType = InterfaceConst.ACCOUNT_TYPE_WLZH;
			UserAccount userAccount = userInfoService.getUserAccount(userId, accountType);
			if(userAccount != null){
				balance = userAccount.getBalance();
			}
			String znxSuffixContent = Sender.get("znx.suffix.content");
			String content = Sender.get("points.exchange.cash.content");
			if(content != null && !"".equals(content)){
				content = content.replace("#{nowDateTime}", DateUtil.getDateTime(new Date())).replace("#{pNum}",String.valueOf(returnpNum)).replace("#{cashAmount}",cashAmount.toString()).replace("#{wlzhAmount}",balance.toString());
				//发送站内信
				if(returnpNum>0 && cashAmount.compareTo(new BigDecimal(0.00)) >0){
					privateMessageService.sendLetter(userId, "系统消息", content+znxSuffixContent);
				}
			}
			
			return response;
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * @title 判断当前用户是否可积分兑换
	 * @param request
	 * @param paramForm
	 * @param token
	 * @param userId
	 * @param pTypeCode
	 * @param pNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cash/canExchanged", method = RequestMethod.POST, headers = APIVersion.V_1_0_0)
    HttpResponse pointsCashCanExchanged(HttpServletRequest request,
    		@ModelAttribute BaseRequestForm  paramForm,
    		@RequestParam(required = true, value = "token") String token,
    		@RequestParam(required = true, value = "userId") String userId,
    		@RequestParam(required = true, value = "pTypeCode") String pTypeCode,
    		@RequestParam(required = true, value = "pNum") String pNum) throws Exception{
		HttpResponse response = new HttpResponse();
		try{
			if(!paramForm.validate() || StringUtils.isEmpty(token) || StringUtils.isEmpty(userId)){
				response.setCodeMessage(ResponseCode.EMPTY_PARAM.getCode(),ResponseCode.EMPTY_PARAM.getMessage());
				return response;
			}
			memberPointsService.pointsCanExchangeCash(Integer.valueOf(userId),pTypeCode,Integer.valueOf(pNum));
			return response;
		}catch(Exception ex){
			throw ex;
		}
	}
	
}