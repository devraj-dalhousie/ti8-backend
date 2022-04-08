package com.dal.diet.controller;

import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class GeneralController {

	private static Map<String, Map<String, List<List<String>>>> fitnessData = new HashMap<String, Map<String, List<List<String>>>>();
	private static Map<String, Map<String, List<String>>> dietData = new HashMap<String, Map<String, List<String>>>();
	private static boolean isStarted = false;

	@RequestMapping(value = "/getFitnessData", method = RequestMethod.GET)
	public Map<String, List<List<String>>> getFitnessData(@RequestParam String age) {
		try {
			init();
			return fitnessData.get("30");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		init();
//	}

	private void init() {
		if (isStarted) {
			return;
		}
		try {
			GeneralController instance = new GeneralController();
			File[] fitnessFiles = new File("/src").listFiles();
			File[] dietFiles = new File("resources/dietPlan").listFiles();

			for (int i =1;i<9;i++) {
				InputStream stream = instance.getClass().getClassLoader().getResourceAsStream("fitnessPlan/"+i+".txt");
				InputStreamReader streamReader =
	                    new InputStreamReader(stream, StandardCharsets.UTF_8);
	             BufferedReader br = new BufferedReader(streamReader);
				StringBuffer sb = new StringBuffer();
				
				String line = br.readLine();
				HashMap<String, List<List<String>>> fitnessDataTemp = new HashMap<String, List<List<String>>>();
				boolean gain = false;
				if(line.startsWith("Weight Gain")) {
					gain = true;
				}else {
					gain = false;
				}
				String age = line.substring(line.length()-2);
					while ((line = br.readLine()) != null) {
						String[] exerciseDetails = line.split(",");
						ArrayList<String> exercise = new ArrayList<String>();

						exercise.add(exerciseDetails[1].trim());
						exercise.add(exerciseDetails[2].trim());
						exercise.add(exerciseDetails[3].trim());
						exercise.add(exerciseDetails[3].trim());
						if(fitnessDataTemp.containsKey(exerciseDetails[0].trim())) {
							List<List<String>> dailyData = fitnessDataTemp.get(exerciseDetails[0]);
							dailyData.add(exercise);
							fitnessDataTemp.replace(exerciseDetails[0], dailyData);
						}else {
						ArrayList<List<String>> dailyData = new ArrayList<List<String>>();
						dailyData.add(exercise);
						fitnessDataTemp.put(exerciseDetails[0].trim(), dailyData);
						}	
				}
					fitnessData.put(age, fitnessDataTemp);
			}
			isStarted = true;
//			
//			for (int i =1;i<5;i++) {
//				InputStream stream = instance.getClass().getClassLoader().getResourceAsStream("dietPlan/"+i+".txt");
//				InputStreamReader streamReader =
//	                    new InputStreamReader(stream, StandardCharsets.UTF_8);
//	             BufferedReader br = new BufferedReader(streamReader);
//				StringBuffer sb = new StringBuffer();
//				
//				String line = br.readLine();
//				HashMap<String, List<List<String>>> fitnessDataTemp = new HashMap<String, List<List<String>>>();
//				boolean gain = false;
//				if(line.startsWith("Weight Gain")) {
//					gain = true;
//				}else {
//					gain = false;
//				}
//				String age = line.substring(line.length()-2);
//					while ((line = br.readLine()) != null) {
//						String[] exerciseDetails = line.split(",");
//						ArrayList<String> exercise = new ArrayList<String>();
//
//						exercise.add(exerciseDetails[1].trim());
//						exercise.add(exerciseDetails[2].trim());
//						exercise.add(exerciseDetails[3].trim());
//						exercise.add(exerciseDetails[3].trim());
//						if(fitnessDataTemp.containsKey(exerciseDetails[0].trim())) {
//							List<List<String>> dailyData = fitnessDataTemp.get(exerciseDetails[0]);
//							dailyData.add(exercise);
//							fitnessDataTemp.replace(exerciseDetails[0], dailyData);
//						}else {
//						ArrayList<List<String>> dailyData = new ArrayList<List<String>>();
//						dailyData.add(exercise);
//						fitnessDataTemp.put(exerciseDetails[0].trim(), dailyData);
//						}	
//				}
//					dietData.put(age, fitnessDataTemp);
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getDietData", method = RequestMethod.GET)
	public HashMap<String, List<String>> getDietData(@RequestParam String age) {
		try {
			//intit();
			HashMap<String, List<String>> dietData = new HashMap<String, List<String>>();

			ArrayList<String> diet = new ArrayList<String>();

			diet.add("BREAKFAST");
			diet.add("LUNCH");
			diet.add("DINNER");
			diet.add("SNACKS 1");
			diet.add("SNACKS 2");

			dietData.put("Monday", diet);
			dietData.put("Tuesday", diet);
			dietData.put("Wednesday", diet);
			dietData.put("Thursday", diet);
			dietData.put("Friday", diet);
			dietData.put("Saturday", diet);
			dietData.put("Sunday", diet);

			return dietData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
