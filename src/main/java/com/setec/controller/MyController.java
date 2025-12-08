package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.setec.entities.Booked;
import com.setec.repo.BookedRepo;
import com.setec.services.MyTelegramBot;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class MyController {

//	http://localhost:8080/
	@GetMapping({ "/", "/home" })
	public String home(Model mod) {
		Booked booked = new Booked(
				1,"Sreng Theanyang",
				"098 849 388",
				"theanyang@gmail.com",
				"11/12/2025",
				"3:21 PM",
				5
				);
		 mod.addAttribute("booked", booked);
		return "index";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/service")
	public String service() {
		return "service";
	}

	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}

	@GetMapping("/reservation")
	public String reservation(Model mod) {
		Booked booked = new Booked(
				1,"Sreng Theanyang",
				"098 849 388",
				"theanyang@gmail.com",
				"11/12/2025",
				"3:21 PM",
				5
				);
		 mod.addAttribute("booked", booked);
		return "reservation";
	}

	@GetMapping("/testimonial")
	public String testimonial() {
		return "testimonial";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	@Autowired
	  private BookedRepo bookedRepo;
	  @Autowired
	  private MyTelegramBot bot;

	  @PostMapping("/success")
	  public String success(@ModelAttribute Booked booked) {
	    bookedRepo.save(booked);
	    String message = String.format(
	    	    "🎉 Booking Confirmed!\n\n" +
	    	    "🆔 Booking ID: `%d`\n" +
	    	    "👤 Name: %s\n" +
	    	    "📞 Phone: %s\n" +
	    	    "✉️ Email: %s\n" +
	    	    "📅 Date: %s\n" +
	    	    "🕐 Time: %s\n" +
	    	    "👥 Guests: %d pax\n\n" +
	    	    "Thank you for choosing us! ❤️\n" +
	    	    "We look forward to seeing you! 🌟",
	    	    booked.getId(),
	    	    booked.getName(),
	    	    booked.getPhoneNumber(),
	    	    booked.getEmail(),
	    	    booked.getDate(),
	    	    booked.getTime(),
	    	    booked.getPerson()
	    	);

	    

	      bot.sendMessage(message);
	  
	    return "success";
	  }
	
}
