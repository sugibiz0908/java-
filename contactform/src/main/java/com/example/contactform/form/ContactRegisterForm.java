package com.example.contactform.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactRegisterForm {
	@NotBlank(message = "名前を入力してください")
	@Size(max = 20,message = "名前を20文字以内で入力してください")
	private String name;
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式が正しくありません")
	private String email;
	
	@NotBlank(message = "メッセージを入力してください")
	@Size(max = 100,message = "メッセージを100文字以内で入力してください")
	private String message;
}
