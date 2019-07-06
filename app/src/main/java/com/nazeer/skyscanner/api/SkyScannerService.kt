package com.nazeer.skyscanner.api

import com.nazeer.skyscanner.models.FlightsResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*


interface SkyScannerService {
    @FormUrlEncoded

    @POST("/apiservices/pricing/v1.0")
    fun createSession(
        @Field("cabinClass")cabinClass :String,
        @Field("country")country :String,
        @Field("currency") currency :String,
        @Field("locale") locale:String,
        @Field("locationSchema") locationSchema:String,
        @Field("originplace") originPlace:String,
        @Field("destinationplace")destinationPlace:String,
        @Field("outbounddate") outboundDate:String,
        @Field("inbounddate")inboundDate:String,
        @Field("adults") adults:Int,
        @Field("children") children:Int,
        @Field("infants") infants:Int
    ): Single<Response<Void>>

    @GET
    fun pollSession(@Url url: String) : Single<FlightsResponse>
}