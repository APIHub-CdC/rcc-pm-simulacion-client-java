package com.cdc.apihub.mx.RCCPM.simulacion.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdc.apihub.mx.RCCPM.simulacion.client.ApiClient;
import com.cdc.apihub.mx.RCCPM.simulacion.client.ApiException;
import com.cdc.apihub.mx.RCCPM.simulacion.client.ApiResponse;
import com.cdc.apihub.mx.RCCPM.simulacion.client.api.RCCPMApi;
import com.cdc.apihub.mx.RCCPM.simulacion.client.model.CatalogoEstados;
import com.cdc.apihub.mx.RCCPM.simulacion.client.model.CatalogoPais;
import com.cdc.apihub.mx.RCCPM.simulacion.client.model.Persona;
import com.cdc.apihub.mx.RCCPM.simulacion.client.model.PersonaDomicilio;
import com.cdc.apihub.mx.RCCPM.simulacion.client.model.PersonaPeticion;
import com.cdc.apihub.mx.RCCPM.simulacion.client.model.ReporteRespuesta;


public class ApiTest {
    private Logger logger = LoggerFactory.getLogger(ApiTest.class.getName());
    private ApiClient apiClient = null;
    private final RCCPMApi api = new RCCPMApi();
    private String xApiKey = null;
    
    @Before()
    public void setUp() {
        this.xApiKey = "your_api_key";
        this.apiClient = api.getApiClient();
        this.apiClient.setBasePath("the_url");
    }
    
    @Test
    public void getReporteCreditoPMTest() throws ApiException {

        PersonaPeticion request = new PersonaPeticion();
        Persona persona = new Persona();
        PersonaDomicilio domicilio = new PersonaDomicilio();
        
        Integer estatusOK = 200;
        Integer estatusNoContent = 204;
        
        try {
            
            domicilio.setDireccion("AV. PASEO DE LA REFORMA 01");
            domicilio.setColoniaPoblacion("GUERRERO");
            domicilio.setDelegacionMunicipio("CUAUHTEMOC");
            domicilio.setCiudad("CIUDAD DE MÉXICO");
            domicilio.setEstado(CatalogoEstados.DF);
            domicilio.setCP("68370");
            domicilio.setPais(CatalogoPais.MX);

            persona.setRFC("EDC930121E01");
            persona.setNombre("RESTAURANTE SA DE CV");
            persona.setDomicilio(domicilio);

            request.setFolioOtorgante("1000001");
            request.setPersona(persona);
            
            ApiResponse<?> response = api.getgenericReporteCreditoPM(xApiKey, request);
  
            Assert.assertTrue(estatusOK.equals(response.getStatusCode()));
            
            if(estatusOK.equals(response.getStatusCode())) {
                ReporteRespuesta responseOK = (ReporteRespuesta) response.getData();
                logger.info("RCC-PM Test: "+responseOK.toString());
            }
            
        }catch (ApiException e) {
            if(!estatusNoContent.equals(e.getCode())) {
                logger.info("Error getReporteCreditoPMTest: "+e.getResponseBody());
            }
            Assert.assertTrue(estatusOK.equals(e.getCode()));
        }        
    }
    
}
