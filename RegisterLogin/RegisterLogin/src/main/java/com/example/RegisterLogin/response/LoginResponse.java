package com.example.RegisterLogin.response;


public class LoginResponse<T> {
    private String code;
    private String message;
    private Data data;
    

    public LoginResponse(String code, String message, Data data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public LoginResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class Data {
        private String username;
        private String token;
        private boolean status;

    

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public Data(String username, String token, boolean status) {
			super();
			this.username = username;
			this.token = token;
			this.status = status;
		}
        

        
    }
}

