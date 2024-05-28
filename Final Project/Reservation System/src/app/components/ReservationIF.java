package app.components;

import java.util.List;

import javax.ws.rs.FormParam;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ReservationIF {

	// Call to Local Host 9997 for Notifier Admin - Send Function
	@POST("http://localhost:9997/email/send")
	@FormUrlEncoded
	public Call<ResponseBody> send(
			@Field("email") String email,
	        @Field("subject") String subject,
	        @Field("message") String message);

    // Call to Local Host 9998 for Classes Schedule All - Get Function
    @GET("http://localhost:9998/class-schedules/all")
    <Schedule> Call<List<Schedule>> getAllSchedules();

    // Call to Local Host 9998 for Classes Schedule All - Send Function
    @POST("http://localhost:9998/class-schedules/rooms_available")
    @FormUrlEncoded
    public Call<ResponseBody> check(
    		@Field("roomTaken") String roomTaken,
    		@Field("daySchedule") String daySchedule,
    		@Field("timeSchedule") String timeSchedule);
}
