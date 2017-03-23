package com.fdu.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fdu.model.Careers;
import com.fdu.model.Credentials;
import com.fdu.model.User;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;

@Path("/home")
public class UploadFileService {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(Credentials credentials) {

		if (credentials.getUsername().equals("arifakram") && credentials.getPassword().equals("arifakram")) {
			return Response.status(200).build();
		} else {
			return Response.status(403).build();
		}
	}

	@GET
	@Path("/users")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response users() {
		// return Response.status(200).entity(output).build();

		/*
		 * return Response.ok() .entity(output)
		 * .header("Access-Control-Allow-Origin", "http://127.0.0.1:51343")
		 * .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
		 * .build();
		 */

		User user1 = new User(1214, "Arif Akram", "Mohammed", "arifakram", "2012903009",
				"10314 conser st, overland park, kansas, 66212", "ACTIVE", "arif@cerner.com", "MASTERS",
				"he graduated in 2014", "Computer Science", null);

		User user2 = new User(1215, "Lance", "Klusener", "lancek", "4567345110",
				"10314 conser st, overland park, California, 94086", "INACTIVE", "klance@cerner.com", "BACHELORS",
				"he plays cricket", "Economics", null);

		List<User> users = Arrays.asList(user1, user2);

		return Response.ok(users).build();

	}

	// This one works too
	/*
	 * @Path("/careers")
	 * 
	 * @POST
	 * 
	 * @Consumes(value={MediaType.APPLICATION_JSON,MediaType.MULTIPART_FORM_DATA
	 * }) public Response applyJob(@FormDataParam("file") InputStream
	 * uploadedInputStream,
	 * 
	 * @FormDataParam("file") FormDataContentDisposition
	 * fileDetail, @FormDataParam("model") Careers careers ) throws IOException
	 * {
	 * 
	 * //System.out.println(bodyPart.getEntity());
	 * System.out.println(careers.toString()); return Response.ok(200).build();
	 * }
	 */

	@Path("/careers")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(FormDataMultiPart form) {

		Map<String, List<FormDataBodyPart>> map = form.getFields();
		// System.out.println(bodyPart.getEntity());
		// System.out.println(careers.toString());
		return Response.ok(200).build();
	}

	@Path("/careers2")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response applyJob(@FormDataParam("model") String careersFormData, @FormDataParam("file") InputStream file,
			@FormDataParam("file") FormDataContentDisposition fileDetail)
			throws JsonParseException, JsonMappingException, IOException {

		Careers careers = new ObjectMapper().readValue(careersFormData, Careers.class);
		String fileName = fileDetail.getFileName();
		String fileType = fileDetail.getType();
		writeToFile(file, "/users/arifakrammohammed/computingservices.pdf");
		return Response.ok(200).build();
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String uploadedFileLocation = "d://uploaded/" + fileDetail.getFileName();

		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}