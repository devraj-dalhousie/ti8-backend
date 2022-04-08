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

	private static Map<String, Map<String, List<List<String>>>> gainsData = new HashMap<String, Map<String, List<List<String>>>>();
	private static Map<String, Map<String, List<List<String>>>> lossData = new HashMap<String, Map<String, List<List<String>>>>();
	private static Map<String, Map<String, List<String>>> dietData = new HashMap<String, Map<String, List<String>>>();
	private static boolean isStarted = false;

	@RequestMapping(value = "/getFitnessData", method = RequestMethod.GET)
	public Map<String, List<List<String>>> getFitnessData(@RequestParam String age, @RequestParam String gainOrLoss) {
		try {
			init();
			if (gainOrLoss.equals("true")) {
				if (Integer.valueOf(age.trim()) <= 30) {
					return gainsData.get("16-30");
				} else if (Integer.valueOf(age.trim()) <= 40) {
					return gainsData.get("30-40");
				} else if (Integer.valueOf(age.trim()) <= 50) {
					return gainsData.get("40-50");
				} else {
					return gainsData.get("0-100");
				}
			} else {
				if (Integer.valueOf(age.trim()) <= 30) {
					return lossData.get("16-30");
				} else if (Integer.valueOf(age.trim()) <= 40) {
					return lossData.get("30-40");
				} else if (Integer.valueOf(age.trim()) <= 50) {
					return lossData.get("40-50");
				} else {
					return lossData.get("0-100");
				}
			}

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

			for (int i = 1; i < 5; i++) {
				InputStream stream = instance.getClass().getClassLoader()
						.getResourceAsStream("fitnessPlan/gain/" + i + ".txt");
				InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(streamReader);

				String line = br.readLine();
				HashMap<String, List<List<String>>> fitnessDataTemp = new HashMap<String, List<List<String>>>();
				String age = line.substring(line.length() - 5);
				while ((line = br.readLine()) != null) {
					// if(line.startsWith("Monday"))
					String[] exerciseDetails = line.split(",");
					ArrayList<String> exercise = new ArrayList<String>();

					exercise.add(exerciseDetails[1].trim());
					exercise.add(exerciseDetails[2].trim());
					exercise.add(exerciseDetails[3].trim());
					exercise.add(exerciseDetails[4].trim());
					if (fitnessDataTemp.containsKey(exerciseDetails[0].trim())) {
						List<List<String>> dailyData = fitnessDataTemp.get(exerciseDetails[0]);
						dailyData.add(exercise);
						fitnessDataTemp.replace(exerciseDetails[0], dailyData);
					} else {
						ArrayList<List<String>> dailyData = new ArrayList<List<String>>();
						dailyData.add(exercise);
						fitnessDataTemp.put(exerciseDetails[0].trim(), dailyData);
					}
				}
				gainsData.put(age, fitnessDataTemp);
			}

			for (int i = 1; i < 5; i++) {
				InputStream stream = instance.getClass().getClassLoader()
						.getResourceAsStream("dietPlan/" + i + ".txt");
				InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(streamReader);

				String line = br.readLine();
				Map<String, List<String>> fitnessDataTemp = new HashMap<String, List<String>>();
				String age = line.substring(10).trim();
				while ((line = br.readLine()) != null) {
					// if(line.startsWith("Monday"))
					String[] exerciseDetails = line.split(",");
					String exercise = new String(exerciseDetails[2].trim());
					
					if (fitnessDataTemp.containsKey(exerciseDetails[0].trim())) {
						List<String> dailyData = fitnessDataTemp.get(exerciseDetails[0]);
						dailyData.add(exercise);
						fitnessDataTemp.replace(exerciseDetails[0], dailyData);
					} else {
						List<String> dailyData = new ArrayList<String>();
						dailyData.add(exercise);
						fitnessDataTemp.put(exerciseDetails[0].trim(), dailyData);
					}
				}
				dietData.put(age, fitnessDataTemp);
			}
			
			for (int i = 5; i < 9; i++) {
				InputStream stream = instance.getClass().getClassLoader()
						.getResourceAsStream("fitnessPlan/" + i + ".txt");
				InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(streamReader);

				String line = br.readLine();
				HashMap<String, List<List<String>>> fitnessDataTemp = new HashMap<String, List<List<String>>>();
				String age = line.substring(line.length() - 5);
				while ((line = br.readLine()) != null) {
					// if(line.startsWith("Monday"))
					String[] exerciseDetails = line.split(",");
					ArrayList<String> exercise = new ArrayList<String>();

					exercise.add(exerciseDetails[1].trim());
					exercise.add(exerciseDetails[2].trim());
					exercise.add(exerciseDetails[3].trim());
					exercise.add(exerciseDetails[4].trim());
					if (fitnessDataTemp.containsKey(exerciseDetails[0].trim())) {
						List<List<String>> dailyData = fitnessDataTemp.get(exerciseDetails[0]);
						dailyData.add(exercise);
						fitnessDataTemp.replace(exerciseDetails[0], dailyData);
					} else {
						ArrayList<List<String>> dailyData = new ArrayList<List<String>>();
						dailyData.add(exercise);
						fitnessDataTemp.put(exerciseDetails[0].trim(), dailyData);
					}
				}
				lossData.put(age, fitnessDataTemp);
			}
				
				isStarted = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getDietData", method = RequestMethod.GET)
	public Map<String, List<String>> getDietData(@RequestParam String healthGoal) {
		init();
		try {
			if (healthGoal.equals("non-veg gain")) {
				return dietData.get("Non-Vegeterian - Weight Gain");
			} else if (healthGoal.equals("non-veg loss")) {
				return dietData.get("Non-Vegeterian - Weight Loss");
			}else if (healthGoal.equals("veg gain")) {
				return dietData.get("Vegeterian - Weight Gain");
			} else if (healthGoal.equals("veg loss")) {
				return dietData.get("Vegeterian - Weight Loss");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
