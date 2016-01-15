package com.zncxi.huaxi.webservice.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

public class ParamException extends RuntimeException {

	private static final long serialVersionUID = 3372444314571999113L;

	public ParamException() {
		super();
	}

	public ParamException(String message) {
		super(message);
	}

	public ParamException(Throwable cause) {
		super(cause);
	}

	public ParamException(String message, Throwable cause) {
		super(message, cause);
	}

	@Provider
	public static class ParamExceptionMapper implements ExceptionMapper<ParamException> {
		public Response toResponse(ParamException exception) {
			return Response.serverError().entity("Invalid parameter").build();
		}
	}
}
