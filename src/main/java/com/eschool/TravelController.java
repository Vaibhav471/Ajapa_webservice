package com.eschool;


import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.eschool.beans.Event;
import com.eschool.beans.HeadWiseReportData;
import com.eschool.beans.Travel;
import com.eschool.beans.TravelDateWiseReportData;
import com.eschool.beans.TravelEventUser;
import com.eschool.beans.User;

@RestController
public class TravelController {	
	@Autowired
    TravelRepository trepo;
	@Autowired
	UserRepository urepo;
	@Autowired
	EventRepository erepo;
	private final TravelService travelService;
    @Autowired
    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }
	
	//-------------------------TO SAVE TRAVEL DETAILS---------------------------------------------------------------
	
	@PostMapping("saveTravelDetails")
	public ResponseEntity<Object> saveTravelDetails(@RequestBody Travel travel) {
		Map<String, String> data = new HashMap<>();

		String message="";
		try {
			trepo.save(travel);
			message = "Data saved successfully";			
		} catch (Exception e) {
			message = e.getMessage();
		}		
		data.put("msg", message);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	//-------------------------------GET ALL TRAVEL OBJECTS FROM DB--------------------------------------------------------------------
	@GetMapping("getAllTravels")
	public List<Travel> getAllTravels(){		
		return trepo.findAll();
	}
	
	@GetMapping("getTravelById/{id}")
	Travel getTravelById(@PathVariable int id) {		
		return trepo.findById(id);
	}
	
	@GetMapping("getTravelReportDateWise/{eventId}")
	List<TravelDateWiseReportData> getTravelReportDateWiseByEventId(@PathVariable int eventId) {	
	List<TravelDateWiseReportData> data=new ArrayList<>();
	String query1="select distinct arrival_date from travel where event_id=? order by arrival_date";
	Connection cn=null;
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		cn=DriverManager.getConnection("jdbc:mysql://localhost/ajapa?user=root&password=root");
		PreparedStatement ps1=cn.prepareStatement(query1);
		ps1.setInt(1, eventId);
		ResultSet rs1=ps1.executeQuery();
		while(rs1.next())
		{
			java.sql.Date arrival_date=rs1.getDate(1);
			String query2="select count(*),count(distinct travel.family_id),COUNT(IF(TIMESTAMPDIFF(year,dob,now())>45, 1, NULL)),COUNT(IF(TIMESTAMPDIFF(year,dob,now())<10, 1, NULL)),COUNT(IF(gender='Male', 1, NULL)),COUNT(IF(gender='Female', 1, NULL)) from travel,user where user.id=travel.user_id and event_id=? and arrival_date=?";
			//String query2="select count(*),count(distinct travel.family_id),COUNT(IF(TIMESTAMPDIFF(year,dob,now())>45, 1, NULL)),COUNT(IF(TIMESTAMPDIFF(year,dob,now())<10, 1, NULL)),COUNT(IF(gender='Male', 1, NULL)),COUNT(IF(gender='Female', 1, NULL)) from travel,user where user.id=travel.user_id and event_id=?";
			PreparedStatement ps2=cn.prepareStatement(query2);
			ps2.setInt(1, eventId);			
			ps2.setDate(2,arrival_date);			
			ResultSet rs2=ps2.executeQuery();
			rs2.next();
			int totalPerson=rs2.getInt(1);			
			int totalFamilies=rs2.getInt(2);
			int totalSeniors=rs2.getInt(3);			
			int totalKids=rs2.getInt(4);
			int totalMale=rs2.getInt(5);			
			int totalFemale=rs2.getInt(6);	
			TravelDateWiseReportData record=new TravelDateWiseReportData(arrival_date.toString(), totalPerson, totalFamilies, totalSeniors, totalKids, totalMale, totalFemale);
			data.add(record);
		}
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	finally {
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return data;
	}
	
	@GetMapping("getTravelReportFamilyWise/{eventId}")
	List<HeadWiseReportData> getTravelReportFamilyWiseByEventId(@PathVariable int eventId) {	
	List<HeadWiseReportData> data=new ArrayList<>();
	String query1="select distinct family_Id from travel where event_id=?";
	Connection cn=null;
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		cn=DriverManager.getConnection("jdbc:mysql://localhost/ajapa?user=root&password=root");
		PreparedStatement ps1=cn.prepareStatement(query1);
		ps1.setInt(1, eventId);
		ResultSet rs1=ps1.executeQuery();
		while(rs1.next())
		{
			HeadWiseReportData record=new HeadWiseReportData();			
			int familyId=rs1.getInt(1);
			String query2="select count(*),COUNT(IF(TIMESTAMPDIFF(year,dob,now())>45, 1, NULL)),COUNT(IF(TIMESTAMPDIFF(year,dob,now())<10, 1, NULL)),COUNT(IF(gender='Male', 1, NULL)),COUNT(IF(gender='Female', 1, NULL)) from travel,user where user.id=travel.user_id and event_id=? and travel.family_id=?";
			PreparedStatement ps2=cn.prepareStatement(query2);
			ps2.setInt(1, eventId);			
			ps2.setInt(2,familyId);			
			ResultSet rs2=ps2.executeQuery();
			rs2.next();
			int totalPerson=rs2.getInt(1);			
			int totalSeniors=rs2.getInt(2);			
			int totalKids=rs2.getInt(3);
			int totalMale=rs2.getInt(4);			
			int totalFemale=rs2.getInt(5);	
			String query3="select id,email,full_name from user where family_id=? and user_type='head'";
			PreparedStatement ps3=cn.prepareStatement(query3);
			ps3.setInt(1, familyId);			
			ResultSet rs3=ps3.executeQuery();
			rs3.next();
			int id=rs3.getInt(1);
			String email=rs3.getString(2);
			String fullName=rs3.getString(3);
			String query4="select u.full_name,u.city,t.arrival_date,t.arrival_mode_of_transport,t.arrival_train_number,t.arrival_train_name,t.departure_date,t.departure_mode_of_transport,t.departure_train_number,t.departure_train_name from travel t,user u where t.user_id=u.id and t.family_id=? and t.event_id=?";
			PreparedStatement ps4=cn.prepareStatement(query4);
			ps4.setInt(1,familyId);	
			ps4.setInt(2, eventId);			
					
			ResultSet rs4=ps4.executeQuery();
		while(rs4.next())
		{
			String memberName=rs4.getString(1);
			String reachingCity=rs4.getString(2);
			java.sql.Date reachingDate=rs4.getDate(3);
			String reachingMode=rs4.getString(4);
			String reachingTrainDetails=rs4.getString(5)+":"+rs4.getString(6);
			java.sql.Date leavingDate=rs4.getDate(7);
			String leavingMode=rs4.getString(8);
			String leavingTrainDetails=rs4.getString(9)+":"+rs4.getString(10);
			record.getMemberNames().add(memberName);
			record.getReachingCity().add(reachingCity);
			record.getReachingDate().add(reachingDate);
			record.getReachingMode().add(reachingMode);
			record.getReachingTrainDetails().add(reachingTrainDetails);			
			record.getLeavingDate().add(leavingDate);
			record.getLeavingMode().add(leavingMode);
			record.getLeavingTrainDetails().add(leavingTrainDetails);
			
			
			
		}
		record.setHeadName(fullName);
		record.setEmailId(email);
		record.setTotalKids(totalKids);
		record.setTotalFemaleMembers(totalFemale);
		record.setTotalMaleMembers(totalMale);
		record.setTotalPersons(totalPerson);
		record.setTotalSeniorCitizens(totalSeniors);
		
		data.add(record);
		}
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	finally {
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return data;
	}
	
	
	
	
	
	@GetMapping("getTravelReportDateWise/{eventId}/{isDesc}")
	List<TravelDateWiseReportData> getTravelReportDateWiseByEventId(@PathVariable int eventId,@PathVariable int isDesc) {	
	List<TravelDateWiseReportData> data=new ArrayList<>();
	String query1="select distinct departure_date from travel where event_id=? order by departure_date";
	Connection cn=null;
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		cn=DriverManager.getConnection("jdbc:mysql://localhost/ajapa?user=root&password=root");
		PreparedStatement ps1=cn.prepareStatement(query1);
		ps1.setInt(1, eventId);
		ResultSet rs1=ps1.executeQuery();
		while(rs1.next())
		{
			java.sql.Date departure_date=rs1.getDate(1);
			String query2="select count(*),count(distinct travel.family_id),COUNT(IF(TIMESTAMPDIFF(year,dob,now())>45, 1, NULL)),COUNT(IF(TIMESTAMPDIFF(year,dob,now())<10, 1, NULL)),COUNT(IF(gender='Male', 1, NULL)),COUNT(IF(gender='Female', 1, NULL)) from travel,user where user.id=travel.user_id and event_id=? and departure_date=?";
			//String query2="select count(*),count(distinct travel.family_id),COUNT(IF(TIMESTAMPDIFF(year,dob,now())>45, 1, NULL)),COUNT(IF(TIMESTAMPDIFF(year,dob,now())<10, 1, NULL)),COUNT(IF(gender='Male', 1, NULL)),COUNT(IF(gender='Female', 1, NULL)) from travel,user where user.id=travel.user_id and event_id=?";
			PreparedStatement ps2=cn.prepareStatement(query2);
			ps2.setInt(1, eventId);			
			ps2.setDate(2,departure_date);			
			ResultSet rs2=ps2.executeQuery();
			rs2.next();
			int totalPerson=rs2.getInt(1);			
			int totalFamilies=rs2.getInt(2);
			int totalSeniors=rs2.getInt(3);			
			int totalKids=rs2.getInt(4);
			int totalMale=rs2.getInt(5);			
			int totalFemale=rs2.getInt(6);	
			TravelDateWiseReportData record=new TravelDateWiseReportData(departure_date.toString(), totalPerson, totalFamilies, totalSeniors, totalKids, totalMale, totalFemale);
			data.add(record);
		}
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	finally {
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return data;
	}
	
	
	@GetMapping("getTravelByUserId/{id}")
	public List<TravelEventUser> getTravelByUserId(@PathVariable int id) {
		
		List<Travel> t= trepo.findAllByUserId(id);
List<TravelEventUser> t1=new ArrayList<>();
		
		for(Travel tt:t) {
			TravelEventUser teu=new TravelEventUser();
			teu.setTravelId(tt.getTravelId());
			teu.setEventId(tt.getEventId());
			teu.setUserId(tt.getUserId());
			teu.setFromCity(tt.getFromCity());
			teu.setFromCountry(tt.getFromCountry());
			teu.setArrivalDate(tt.getArrivalDate());
			teu.setArrivalTime(tt.getArrivalTime());
			teu.setArrivalModeOfTransport(tt.getArrivalModeOfTransport());
			teu.setArrivalTrainNumber(tt.getArrivalTrainNumber());
			teu.setArrivalTrainName(tt.getArrivalTrainName());
			teu.setDepartureDate(tt.getDepartureDate());
			teu.setDepartureTime(tt.getDepartureTime());
			teu.setDepartureModeOfTransport(tt.getDepartureModeOfTransport());
			teu.setDepartureTrainName(tt.getDepartureTrainName());
			teu.setDescription(tt.getDescription());
			teu.setUserName(urepo.findUserNameByUserId(tt.getUserId()));
			teu.setEventName(erepo.findEventNameByUserId(tt.getEventId()));
			t1.add(teu);
		}		
		return t1;	
	}	
	@GetMapping("getAllTravel1")
	public List<TravelEventUser> getAllTravel1() {		
		List<Travel> t= trepo.findAll();
		List<TravelEventUser> t1=new ArrayList<>();		
		for(Travel tt:t) {
			TravelEventUser teu=new TravelEventUser();
			teu.setTravelId(tt.getTravelId());
			teu.setEventId(tt.getEventId());
			teu.setUserId(tt.getUserId());
			teu.setFromCity(tt.getFromCity());
			teu.setFromCountry(tt.getFromCountry());
			teu.setArrivalDate(tt.getArrivalDate());
			teu.setArrivalTime(tt.getArrivalTime());
			teu.setArrivalModeOfTransport(tt.getArrivalModeOfTransport());
			teu.setArrivalTrainNumber(tt.getArrivalTrainNumber());
			teu.setArrivalTrainName(tt.getArrivalTrainName());
			teu.setDepartureDate(tt.getDepartureDate());
			teu.setDepartureTime(tt.getDepartureTime());
			teu.setDepartureModeOfTransport(tt.getDepartureModeOfTransport());
			teu.setDepartureTrainName(tt.getDepartureTrainName());
			teu.setDescription(tt.getDescription());
			teu.setUserName(urepo.findUserNameByUserId(tt.getUserId()));
			teu.setEventName(erepo.findEventNameByUserId(tt.getEventId()));

			t1.add(teu);
		}
		
		return t1;
		
	}
	
	@GetMapping("getAllTravelEventUser/{id}")
	public List<TravelEventUser> getAllTravelEventUser(@PathVariable int id) {
		System.out.println("Hell0");
		List<Travel> t= trepo.findAllByEventId(id);		
		List<TravelEventUser> t1=new ArrayList<>();		
		for(Travel tt:t) {			
			TravelEventUser teu=new TravelEventUser();
			teu.setTravelId(tt.getTravelId());
			teu.setEventId(tt.getEventId());
			teu.setUserId(tt.getUserId());
			teu.setFromState(tt.getFromState());
			teu.setFromCity(tt.getFromCity());			
			teu.setFromCountry(tt.getFromCountry());
			teu.setArrivalDate(tt.getArrivalDate());
			teu.setArrivalTime(tt.getArrivalTime());
			teu.setArrivalModeOfTransport(tt.getArrivalModeOfTransport());
			teu.setArrivalTrainNumber(tt.getArrivalTrainNumber());
			teu.setArrivalTrainName(tt.getArrivalTrainName());
			teu.setDepartureDate(tt.getDepartureDate());
			teu.setDepartureTime(tt.getDepartureTime());
			teu.setDepartureModeOfTransport(tt.getDepartureModeOfTransport());
			teu.setDepartureTrainName(tt.getDepartureTrainName());
			teu.setDescription(tt.getDescription());			
			User user=urepo.findById(tt.getUserId());
			teu.setUserName(user.getFullName());
			Event event=erepo.findById(tt.getEventId());
			teu.setEventName(event.getEventName());
			t1.add(teu);

		}
		
		return t1;
		
	}
	@GetMapping("getAllTravelEventUser/{eventId}/{familyId}")
	public List<TravelEventUser> getAllTravelEventUserByEventIdFamilyId(@PathVariable int eventId,@PathVariable int familyId ) {
		System.out.println("Hell0");
		List<Travel> t= trepo.findAllByEventIdAndFamilyId(eventId,familyId);		
		List<TravelEventUser> t1=new ArrayList<>();		
		for(Travel tt:t) {			
			TravelEventUser teu=new TravelEventUser();
			teu.setTravelId(tt.getTravelId());
			teu.setEventId(tt.getEventId());
			teu.setUserId(tt.getUserId());
			teu.setFromCity(tt.getFromCity());
			teu.setFromState(tt.getFromState());
     		teu.setFromCountry(tt.getFromCountry());
			teu.setArrivalDate(tt.getArrivalDate());
			teu.setArrivalTime(tt.getArrivalTime());
			teu.setArrivalModeOfTransport(tt.getArrivalModeOfTransport());
			teu.setArrivalTrainNumber(tt.getArrivalTrainNumber());
			teu.setArrivalTrainName(tt.getArrivalTrainName());
			teu.setDepartureDate(tt.getDepartureDate());
			teu.setDepartureTime(tt.getDepartureTime());
			teu.setDepartureModeOfTransport(tt.getDepartureModeOfTransport());
			teu.setDepartureTrainName(tt.getDepartureTrainName());
			teu.setDescription(tt.getDescription());
			
			User user=urepo.findById(tt.getUserId());
			teu.setUserName(user.getFullName());
			Event event=erepo.findById(tt.getEventId());
			teu.setEventName(event.getEventName());
			t1.add(teu);

		}
		
		return t1;
		
	}

	
	//-------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("getTravelByEventAndTravel/{eventId}/{familyId}")
	public List<Travel> getTravelByEventAndTravel(@PathVariable int eventId, @PathVariable int familyId){	
		return trepo.findAllByEventIdAndFamilyId(eventId, familyId);
	}
	
	//----------------------------------------------------------------------------------
	@PutMapping("updateTravel")
	public String updateTravel(@RequestBody Travel travel) {
		String message;
		
		try {
		Travel t=trepo.findById(travel.getTravelId());
		
		t.setTravelId(travel.getTravelId());
		t.setEventId(travel.getEventId());
		t.setUserId(travel.getUserId());
		t.setFromCity(travel.getFromCity());
		t.setFromCountry(travel.getFromCountry());
		t.setArrivalDate(travel.getArrivalDate());
		t.setArrivalTime(travel.getArrivalTime());
		t.setArrivalModeOfTransport(travel.getArrivalModeOfTransport());
		t.setArrivalTrainNumber(travel.getArrivalTrainNumber());
		t.setArrivalTrainName(travel.getArrivalTrainName());
		t.setDepartureDate(travel.getDepartureDate());
		t.setDepartureTime(travel.getDepartureTime());
		t.setDepartureModeOfTransport(travel.getDepartureModeOfTransport());
		t.setDepartureTrainName(travel.getDepartureTrainName());
		t.setDescription(travel.getDescription());
		
		trepo.save(t);
		
		message="Updated";
		
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		
		return message;
	}
	//---------------------------------------------------------------------------------------------------------------------
	@GetMapping("getTravelByFamilyId/{id}")
	public List<Travel>  getTravelByFamilyId(@PathVariable int id){
		
		return trepo.findAllByFamilyId(id);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	@GetMapping("deleteTravel/{id}")
	public String deleteTravel(@PathVariable int id)
	{
		String message="";
		try {
		trepo.deleteById(id);
		message="deleted";
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		
		return message;
	}
	//---------------------------------------------------------------------------------------------------------------------------
	@GetMapping("generatePdf")
    public void generatePdf(@RequestParam String outputPath) {
        List<Travel> travelData = trepo.findAll();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(100, 700);
                for (Travel item : travelData) {
                    contentStream.showText("ID: " + item.getTravelId() + ", EventId: " + item.getEventId() + ", UserId: " + item.getUserId() + ", from city: " + item.getFromCity() + ", from country: " + item.getFromCountry() + ", arrival date: " + item.getArrivalDate() + ", arrival time: " + item.getArrivalTime() + ", arrival mode of transport: " + item.getArrivalModeOfTransport() + ", arrival train number: " + item.getArrivalTrainNumber() + ",departure date: " + item.getDepartureDate() + ",departure time: " + item.getDepartureTime() + ",departure mode of transport" + item.getDepartureModeOfTransport() + ",departure train number: " + item.getDepartureTrainNumber() + ", departure train name: " + item.getDepartureTrainName() + ", description: " + item.getDescription() + ", familyId: " + item.getFamilyId());
                    contentStream.newLine();
                }
                contentStream.endText();
            }

            document.save(outputPath);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly
        }
    }
	//-------------------------------------------------------------------------------------------------------------
	@GetMapping("generateExcel")
    public void generateExcel(@RequestParam String outputPath) {
        List<Travel> travelData = trepo.findAll(); // Retrieve travel data from your data source

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Travel Data");
            Row headerRow = sheet.createRow(0);

            // Create header cells
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("EventId");
            headerRow.createCell(2).setCellValue("UserId");
            headerRow.createCell(3).setCellValue("from city");
            headerRow.createCell(4).setCellValue("from country");
            headerRow.createCell(5).setCellValue("arrival date");
            headerRow.createCell(6).setCellValue("arrival mode of transport");
            headerRow.createCell(7).setCellValue("arrival train number");
            headerRow.createCell(8).setCellValue("arrival train name");
            headerRow.createCell(9).setCellValue("departure date");
            headerRow.createCell(10).setCellValue("departure time");
            headerRow.createCell(11).setCellValue("departure mode of transport");
            headerRow.createCell(12).setCellValue("departure train number");
            headerRow.createCell(13).setCellValue("departure train name");
            headerRow.createCell(14).setCellValue("description");
            headerRow.createCell(15).setCellValue("FamilyId");


            int rowNum = 1;
            for (Travel item : travelData) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(item.getTravelId());
                row.createCell(1).setCellValue(item.getEventId());
                row.createCell(2).setCellValue(item.getUserId());
                row.createCell(3).setCellValue(item.getFromCity());
                row.createCell(4).setCellValue(item.getFromCountry());
                row.createCell(5).setCellValue(item.getArrivalDate());
                row.createCell(6).setCellValue(item.getArrivalTime());
                row.createCell(7).setCellValue(item.getArrivalModeOfTransport());
                row.createCell(8).setCellValue(item.getArrivalTrainNumber());
                row.createCell(9).setCellValue(item.getArrivalTrainName());
                row.createCell(10).setCellValue(item.getDepartureDate());
                row.createCell(11).setCellValue(item.getDepartureTime());
                row.createCell(12).setCellValue(item.getDepartureModeOfTransport());
                row.createCell(13).setCellValue(item.getDepartureTrainNumber());
                row.createCell(14).setCellValue(item.getDepartureTrainName());
                row.createCell(15).setCellValue(item.getDescription());

            }

            try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
                workbook.write(outputStream);
            } catch (IOException e) {
                e.printStackTrace(); // Handle exceptions properly
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions properly
        }
    }

	@GetMapping("getTravelByEventId/{id}")
	public List<Travel>  getTravelByEventId(@PathVariable int id){
		
		return trepo.findAllByEventId(id);
	}
    }
	


