package com.example.contactform.controller;
import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.contactform.entity.ContactForm;
import com.example.contactform.form.ContactRegisterForm;
import com.example.contactform.repository.ContactRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	private final ContactRepository contactRepository;
	private final JdbcTemplate jdbcTemplate;
	
	public HomeController(ContactRepository contactRepository,JdbcTemplate jdbcTemplate) {
		this.contactRepository = contactRepository;
		this.jdbcTemplate = jdbcTemplate;
	}

    @GetMapping("/form")
    public String getForm(Model model) {
    	
        model.addAttribute("form", new ContactForm());
        return "form";
    }
    
	//formタグにバインドするformクラスインスタンスに紐づける
	@ModelAttribute
	public ContactRegisterForm setUpForm() {
		return new ContactRegisterForm();
	}
	
    @PostMapping("/form/result")
    public String confirmView(@Validated ContactRegisterForm form,BindingResult bindingResult,Model model,RedirectAttributes redirectAttributes, Locale locale,HttpSession session){
    	//入力チェック
    	if(bindingResult.hasErrors()) {
    		return "form";
    	}
    	// ContactForm エンティティにデータをコピー
        ContactForm contactForm = new ContactForm();
        contactForm.setName(form.getName());
        contactForm.setEmail(form.getEmail());
        contactForm.setMessage(form.getMessage());

        // データベースに保存
        contactRepository.save(contactForm);
        // ログインユーザーの account.id を取得
        Integer accountId = (Integer) session.getAttribute("account.id");
        
        contactForm.setOwnerId(accountId);
        
        // contactFormエンティティをデータベースに保存
        contactRepository.saveAndFlush(contactForm);
        
        // テンプレートにデータを渡す
        model.addAttribute("form", contactForm); // contactFormを渡す
        
    	return "result";
    }
    
    @GetMapping("/form/list")
    public String getFormList(Model model,@PageableDefault(page = 0,size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,HttpSession session) {
    	Integer accountId = (Integer)session.getAttribute("accountId");
    	Page<ContactForm> contactPage = contactRepository.findByOwnerId(accountId, pageable);
    	model.addAttribute("contactPage",contactPage);

        return "list";
    }


    @GetMapping("/form/delete")
    public String deleteUser(Model model) {
        List<ContactForm> contacts = contactRepository.findAll();

        model.addAttribute("contacts", contacts);
        return "delete";
    }

    @PostMapping("/form/delete")
    public String deleteSelectedContacts(@RequestParam("selectedContact") Long selectedContactId) {
        jdbcTemplate.update("DELETE FROM contact WHERE id = ?", selectedContactId);
        return "redirect:/form/list";
    }
}
