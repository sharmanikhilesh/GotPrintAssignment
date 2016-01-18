package org.gotprint.assignment.usernotes.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.hibernate.HibernateException;

public class HibernateExceptionMapper implements ExceptionMapper<HibernateException> {

	@Override
	public Response toResponse(HibernateException arg0) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

}
