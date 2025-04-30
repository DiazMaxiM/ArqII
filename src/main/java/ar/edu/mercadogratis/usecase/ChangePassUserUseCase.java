package ar.edu.mercadogratis.usecase;

import org.json.JSONException;
import org.json.JSONObject;

public interface ChangePassUserUseCase {
    public Long changePassword(JSONObject userWithNewPassword) throws RuntimeException, JSONException;
}
