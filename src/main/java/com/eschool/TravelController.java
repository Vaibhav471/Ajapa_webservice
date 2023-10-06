package com.eschool;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

import com.eschool.beans.Travel;
import com.eschool.beans.TravelEventUser;

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
	
	@GetMapping("getTravelByUserId/{id}")
	public List<TravelEventUser> getTravelByUserId(@PathVariable int id) {
		
		List<Travel> t= trepo.findAllByUserId(id);
List<TravelEventUser> t1=new ArrayList();
		
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
		List<TravelEventUser> t1=new ArrayList();
		
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
	


