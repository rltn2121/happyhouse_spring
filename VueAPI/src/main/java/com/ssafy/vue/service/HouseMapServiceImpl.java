package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.HouseDealInfoDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong, double myLat, double myLng) throws Exception {
		List<HouseInfoDto> list = houseMapMapper.getAptInDong(dong);
		list.forEach(dto -> dto.setDist(getDist(dto, myLat, myLng)));
		
		Collections.sort(list, (a,b) -> (a.getDist() > b.getDist()) ? 1 : -1);
		return list;
	}

	private double getDist(HouseInfoDto a, double myLat, double myLng) {
		System.out.println("String -> " + a.getLat() + ", " + a.getLng());
		// A의 위도, 경도
		double aLat = Double.parseDouble(a.getLat());
		double aLng = Double.parseDouble(a.getLng());
		
		System.out.println("Double -> " + aLat + ", " + aLng);
		Dms aLatDms = getDmsByLatLng(aLat);
		Dms aLngDms = getDmsByLatLng(aLng);
		System.out.println("aLatDms: " + aLatDms);
		System.out.println("aLngDms: " + aLngDms);
		
		Dms myLatDms = getDmsByLatLng(myLat);
		Dms myLngDms = getDmsByLatLng(myLng);
		System.out.println("myLatDms: " + myLatDms);
		System.out.println("myLngDms: " + myLngDms);
		
		Dms latDiffMyA = new Dms(aLatDms.d - myLatDms.d, aLatDms.m - myLatDms.m, aLatDms.s - myLatDms.s);
		Dms lngDiffMyA = new Dms(aLngDms.d - myLngDms.d, aLngDms.m - myLngDms.m, aLngDms.s - myLngDms.s);
		double distMyA = getDistanceByDms(latDiffMyA.d, latDiffMyA.m, latDiffMyA.s, lngDiffMyA.d, lngDiffMyA.m, lngDiffMyA.s);
		System.out.println(distMyA);
		return distMyA;
	}
	
	// 위도, 경도를 DMS로 표현
	private Dms getDmsByLatLng(double dd) {
		double d = (int)dd;
		double temp = (dd-d)*60;
		double m = (int)(temp);
		double s = (temp - m) * 60;
		return new Dms(d,m,s);
	}


	//DMS 연산 결과로 거리 구하기
	Double getDistanceByDms(Double latDeg, Double latMin, Double latSec,Double lonDeg, Double lonMin, Double lonSec) {
		double lat = latDeg*88.9036+latMin*1.4817+latSec*0.0246;
		double lng = lonDeg*111.3194+lonMin*1.8553+lonSec*0.0309;
		
		System.out.println("getDistanceByDms -> lat: " + lat);
		System.out.println("getDistanceByDms -> lon: " + lng);
		
	    return Math.sqrt( lat*lat + lng*lng);
	}
	
	class Dms{
		double d;
		double m;
		double s;
		public Dms(double d, double m, double s) {
			this.d = d;
			this.m = m;
			this.s = s;
		}
		@Override
		public String toString() {
			return "Dms [d=" + d + ", m=" + m + ", s=" + s + "]";
		}
	}

	@Override
	public List<HouseDealInfoDto> findById(int aptCode) throws SQLException {
		return houseMapMapper.findById(aptCode);
	}

}
