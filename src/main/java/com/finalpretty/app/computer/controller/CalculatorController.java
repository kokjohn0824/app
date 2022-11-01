package com.finalpretty.app.computer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalpretty.app.Response.DailyRecordDto;
import com.finalpretty.app.model.DailyRecord;
import com.finalpretty.app.repositories.DailyRecordRespository;

@Controller
public class CalculatorController {

	@Autowired
	private DailyRecordRespository drecordDao;

	@GetMapping("/metabolic")
	public String metabolic() {
		return "computer/calculator";
	}

	@ResponseBody
	@GetMapping("/public/api/chartBmi/{member_id}/{addDate}/{dateEnd}")
	public List<DailyRecordDto> chartBmi(@PathVariable("member_id") Integer member_id,
			@PathVariable("addDate") String addDate,
			@PathVariable("dateEnd") String dateEnd) {
		List<DailyRecord> dRecord = drecordDao.chartBmi(member_id, addDate, dateEnd);
		List<DailyRecordDto> recordDtolist = new ArrayList<>();
		DailyRecordDto recordDto;
		if (dRecord == null) {
			return null;
		}
		for (DailyRecord i : dRecord) {
			recordDto = new DailyRecordDto();
			recordDto.setDaily_record_id(i.getDaily_record_id());
			recordDto.setDrinkingWater(i.getDrinkingWater());
			recordDto.setWeight(i.getWeight());
			recordDto.setBodyFat(i.getBodyFat());
			Double bmi = (double) (i.getWeight()) / Math.pow((double) (i.getMembers().getHeight() / 100), 2);
			recordDto.setBmi(bmi);
			recordDto.setDate_time(i.getDate_time());
			recordDtolist.add(recordDto);
		}

		return recordDtolist;
	}
}
