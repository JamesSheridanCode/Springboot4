package com.example.packagetest;


import com.example.packagetest.dao.PetitionDAO;
import com.example.packagetest.dao.SignDAO;
import com.example.packagetest.entity.Petition;
import com.example.packagetest.entity.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import java.util.List;

@SpringBootApplication
@RestController
public class PackagetestApplication {

	//private PetitionDAO petitionDAO ;
	//private SignDAO signDAO;

	@Autowired
	public PackagetestApplication() {

	}


	//for testing 2
	@GetMapping("/viewpetition")
	public String viewpetionl() {

		return "View  Petitions Page!";
	}

	public static void main(String[] args) {
		SpringApplication.run(PackagetestApplication.class, args);
	}

	@Bean
	public CommandLineRunner  commandLineRunner (PetitionDAO petitionDAO,SignDAO signDAO){

		return runner -> {

			//createPetiton(petitionDAO);
			//readPetition(petitionDAO);
			//queryForPetitions(petitionDAO);
			//queryForPetitionsBySearchTerm( petitionDAO);
			//createSign( signDAO);
			//getAllSignsWithId( signDAO, (long) 1l);
			//getSignsCountWithId(signDAO,(long) 1l);
		};
	}

	private void queryForPetitionsBySearchTerm(PetitionDAO petitionDAO) {
		List<Petition> thePetitions = petitionDAO.findBySearchTerm("dog");

		for(Petition tempPetition : thePetitions){
			System.out.println(tempPetition);
		}
	}

	private void queryForPetitions(PetitionDAO petitionDAO) {
		List<Petition> thePetitions = petitionDAO.findAll();

		for(Petition tempPetition : thePetitions){
			System.out.println(tempPetition);
		}
	}

	private void getAllSignsWithId(SignDAO signDAO,Long id){
		List<Sign> theSigns = signDAO.findAllByPetitionId(id);
		for(Sign tempSign : theSigns){
			System.out.println(tempSign);
		}
	}

	private void getSignsCountWithId(SignDAO signDAO,Long id){
		int count = signDAO.getSignCountById(id);

		System.out.println("Sign count for id" + id + " is " + count);
	}

	private void readPetition(PetitionDAO petitionDAO) {

		// create a petition object
		System.out.println("Creating petition object..");
		Petition tempPetition = new Petition("Ban all dogs in the park","No more","karen1","karen1@gmail.comt");
		//save the petition
		System.out.println("Saving petition object..");
		petitionDAO.save(tempPetition);
		//display id
         long theId =  tempPetition.getId();
		//retirieve
		System.out.println("Retrieving student object..");
		Petition myPetition = petitionDAO.findById(theId);
		//display
		System.out.println("Found petition.." + myPetition);
	}

	private void createPetiton(PetitionDAO petitionDAO){
		System.out.println("Creating petition object..");
		Petition tempPetition = new Petition("test","test","test","test");

		System.out.println("Saving petition object..");
		petitionDAO.save(tempPetition);

		System.out.println("Saved petition Generated id:." + tempPetition.getId());

	}

	private void createSign(SignDAO signDAO){
		System.out.println("Creating sign object..");
		Sign tempSign = new Sign("jilly","I aggree 2", 1L);
		System.out.println("Saving sign object..");
		signDAO.save(tempSign);
		System.out.println("Saved sign Generated id:." + tempSign.getSignId());
	}

}
