package com.example.demo.Beans;

import java.io.Serializable;

import lombok.*;;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class User implements Serializable
{
	private long userid;
	@NonNull
	private String email;
	@NonNull
	private String encryptedPassword;
	private boolean enabled;
	
	
}
