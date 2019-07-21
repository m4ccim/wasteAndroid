package com.example.maxim.IMAPA;

import com.example.maxim.IMAPA.Models.Bill;
import com.example.maxim.IMAPA.Models.BillDetails;
import com.example.maxim.IMAPA.Models.Card;
import com.example.maxim.IMAPA.Models.CardWaste;
import com.example.maxim.IMAPA.Models.Product;
import com.example.maxim.IMAPA.Models.ProductWaste;
import com.example.maxim.IMAPA.Models.TokenPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceholderApi {



    @GET("cards/{id}")
    Call<Card> getCard(@Path("id") int id);

    @PUT("cards/{id}")
    Call<Void> updateCard(@Path("id") int id, @Body Card card);

    @GET("cardWastes/cardId/{id}")
    Call<List<CardWaste>> getCardWastesByCardId (@Path("id") int id);


    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") int id);

    @GET("productWastes/product/{id}")
    Call<List<ProductWaste>> getProductWastes(@Path("id") int id);

    @POST("account/token")
    Call<TokenPost> getToken(@Body Card user);


    @GET("bills")
    Call<List<Bill>> getBills();

    @GET("bills/cardId/{id}")
    Call<List<Bill>> getBillsByCard(@Path("id") int id);

    @GET("bills/Details/{id}")
    Call<BillDetails> getBillDetails(@Path("id") int id);

    @GET("invoices/{id}")
    Call<Bill> getInvoice(@Path("id") int id);


    //@POST("customers/addcustomer")


    //Call<Void> addCustomer(@Body Customer customer);


    //


    //@GET("machines/getBrokenMachines/{id}")


    //Call<List<Machine>> getBroken(@Path("id") int id);


    //


    //@PUT("customers/updatecustomer/{id}")


    //Call<Void> updateCustomer(@Path("id") int id, @Body Customer customer);


    //


    //@DELETE("customers/{id}")


    //Call<Void> deleteCustomer(@Path("id") int id);


    //


    //@GET("users/getuserbyid/{id}")


    //Call<Card> getUserById(@Path("id") int id);


    //


    //@POST("users/adduser")


    //Call<Void> addUser(@Body Card user);


    //


    //@PUT("users/updateuser/{id}")


    //Call<Void> updateUser(@Path("id") int id, @Body Card user);


    //


    //@DELETE("users/deleteuser/{id}")


    //Call<Void> deleteUser(@Path("id") int id);


    //


    //@GET("products/getproducts")


    //Call<List<product>> getProducts();


    //


    //@GET("products/getproductbyid/{id}")


    //Call<product> getProductById(@Path("id") int id);


    //


    //@GET("products/getmachinesproducts/{machId}")


    //Call<List<product>> getMachinesProducts(@Path("machId") int machId);


    //


    //@GET("products/getcustomersproducts/{id}")


    //Call<List<product>> getCustomersProducts(@Path("id") int id);


    //


    //@POST("products/addproduct")


    //Call<Void> addProduct(@Body product product);


    //


    //@PUT("products/updateproduct/{id}")


    //Call<Void> updateProduct(@Path("id") int id);


    //


    //@DELETE("products/deleteproduct/{id}")


    //Call<Void> deleteProduct (@Path("id") int id);


    //


    //@GET("magneticcards/getmagneticcards")


    //Call<List<MagneticCard>> getMagneticCards();


    //


    //@GET("magneticcards/getmagneticcardbyid/{id}")


    //Call<MagneticCard> getMagneticCardById(@Path("id") int id);


    //


    //@PUT("magneticcards/updatemagneticcard/{id}")


    //Call<Void> updateMagneticCard(@Path("id") int id, @Body MagneticCard magneticCard);


    //


    //@POST("magneticcards/addmagneticcard")


    //Call<Void> addMagneticCard(@Body MagneticCard magneticCard);


    //


    //@DELETE("magneticcards/deletemagneticcard/{id}")


    //Call<Void> deleteMagneticCard(@Path("id") int id);


    //


    //@GET("machines/GetMachines")


    //Call<List<Machine>> getMachines();


    //


    //@GET("machines/GetMachineById/{id}")


    //Call<Machine> GetMachineById(@Path("id") int id);


    //


    //@GET("machines/GetCustomersMachines/{cusId}")


    //Call<List<Machine>> GetCustomersMachines(@Path("cusId") int cusId);


    //


    //@POST("machines/AddMachine")


    //Call<Void> AddMachine(@Body Machine machine);


    //


    //@PUT("machines/UpdateMachine/{id}")


    //Call<Void> UpdateMachine(@Path("id") int id, @Body Machine machine);


    //


    //@DELETE("machines/DeleteMachine/{id}")


    //Call<Void> DeleteMachine(@Path("id") int id);


    //


    //@GET("machineuses/getuses")


    //Call<List<MachineUse>> getMachineUses();


    //


    //@GET("machineuses/getusesbyid/{id}")


    //Call<MachineUse> getUseById(@Path("id") int id);


    //


    //@GET("machineuses/getmachines/{id}")


    //Call<List<Machine>> getMachines(@Path("id") int id);


    //


    //@GET("machineuses/getcards/{id}")


    //Call<List<MagneticCard>> getMagneticCards(@Path("id") int id);


    //


    //@GET("machineuses/GetCustomersUses/{customerId}")


    //Call<List<MachineUse>> getCustomersUses(@Path("customerId") String customerId);


    //


    //@GET("machineuses/getstatistics")


    //Call<Statistics> getStatistics(@Query("cardId") String cardId, @Query("month") int month);


    //


    //


    //@GET("machineuses/GetCertainStatistics")


    //Call<CertainStatistics> getCertainStatistics(@Query("cardId") String cardId,


    //@Query("machId") int machId,


    //@Query("month") int month);


    //


    //@GET("machineuses/GetCertainStatisticsForAllMachines")


    //Call<List<CertainStatistics>> getCertainStatisticsForAllMachines(@Query("cardId") String cardId,


    //@Query("month") int month);


    //


    //@GET("machineuses/filter")


    //Call<List<MachineUse>> filter(@Query("machId") int machId,


    //@Query("cardId") int cardId,


    //@Query("prodId") int prodId,


    //@Query("month") int month);


    //


    //@PUT("machineuses/updateuse/{id}")


    //Call<MachineUse> updateUse(@Path("id") int id, @Body MachineUse machineUse);


    //


    //@POST("machineuses/adduse")


    //Call<Void> addUse(@Body MachineUse machineUse);


    //


    //@DELETE("machineuses/deleteuse/{id}")


    //Call<MachineUse> deleteUse(@Path("id") int id);


    //


    //@GET("users/getusers")


    //Call<List<Card>> getUsers();


    //



}
