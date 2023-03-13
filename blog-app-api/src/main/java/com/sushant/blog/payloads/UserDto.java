package com.sushant.blog.payloads;







import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	private int id;
	@NotEmpty
	@Size(min=4,max=10,message="User name must be 4 min characters")
	private String name;
	@Email(message="Email is invalid")
	private String email;
	@NotEmpty
//	@Pattern(regexp="\"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$\"")
	private String password;
	@NotEmpty
	private String about;

}
