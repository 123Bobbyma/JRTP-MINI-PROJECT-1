package in.ashokit.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Plan;
import in.ashokit.service.PlanService;


@RestController
public class PlanRestController {

	@Autowired
	private PlanService planService;

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> plancategories(){
		Map<Integer, String> categories = planService.getPlanCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan){
		String responseMsg ="";
		boolean isSaved = planService.savePlan(plan);
		if(isSaved) {
			responseMsg ="plan saved";
		}else {
			responseMsg ="plan not saves";
		}

		return new ResponseEntity<String>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans(){
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<List<Plan>>(allPlans, HttpStatus.OK);


	}

	@GetMapping("plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){

		Plan planById = planService.getPlanById(planId);
		return new ResponseEntity<Plan>(planById, HttpStatus.OK);

	}
    @PutMapping("/plan")
	public ResponseEntity<String> updatePlan(Plan plan){
		boolean isUpdated = planService.updatePlan(plan);
		String msg ="";

		if(isUpdated) {
			msg="plan updated";

		}else {
			msg="plan not updated";
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@DeleteMapping("plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){

		boolean deletePlanById = planService.deletePlanById(planId);
		String msg="";

		if(deletePlanById) {
			msg="plan deleted";

		}else {
			msg="plan not deleted";
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(Integer planId, String status){

		String msg = "";
		boolean statusChange = planService.planStatusChange(planId, status);
		if(statusChange) {
			msg="status changed";

		}else {
			msg="status not changed";
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}









}
