package org.kallinikos.jaxrstuts.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kallinikos.jaxrstuts.messenger.model.ErrorMessage;


//provider registers the class in jax-rs
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
	
	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "https://google.fr");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
