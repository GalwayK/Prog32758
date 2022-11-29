package ca.sheridanc.galwayk.Beans;

import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class UserAccount 
{
	private int accountId;
	@NonNull
	private String username;
	@NonNull
	private String password;
	private boolean enabled;
}
