package com.example.contactform.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.contactform.entity.ContactForm;

@Controller
public class HomeController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}

	@GetMapping("/form")
	public String getForm(Model model) {
		model.addAttribute("form", new ContactForm());
		return "form";
	}

	@PostMapping("/form/result")
	public String getFormResult(@ModelAttribute ContactForm form, Model model) {
		model.addAttribute("form", form);
		jdbcTemplate.update("INSERT INTO contact(id,name,email,message) Values(?,?,?,?)", form.getId(), form.getName(),
				form.getEmail(), form.getMessage());
		return "result";
	}

	@GetMapping("/form/list")
	public String getFormList(Model model) {
		List<Map<String, Object>> contacts = jdbcTemplate.queryForList("select * from contact");

		model.addAttribute("contacts", contacts);
		return "list";
	}

	@GetMapping("/form/delete")
	public String deleteUser(Model model) {
		List<Map<String, Object>> contacts = jdbcTemplate.queryForList("select * from contact");

		model.addAttribute("contacts", contacts);
		return "delete";
	}

	@PostMapping("/form/delete")
	public String deleteSelectedContacts(@RequestParam("selectedContact") Long selectedContactId) {
	    jdbcTemplate.update("DELETE FROM contact WHERE id = ?", selectedContactId);
	    return "redirect:/form/list";
	}

}
