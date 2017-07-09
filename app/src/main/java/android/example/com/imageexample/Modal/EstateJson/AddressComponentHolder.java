package android.example.com.imageexample.Modal.EstateJson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressComponentHolder {
    private AddressComponent[] addressComponents;

    public AddressComponentHolder() {

    }

    public AddressComponent[] getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(AddressComponent[] addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getPostalCode() {
        if( addressComponents == null || addressComponents.length <= 0 ) {
            return null;
        } else {
            for(AddressComponent addressComponent : addressComponents) {
                if(addressComponent.isPostalCodeComponent()) {
                    return addressComponent.getName();
                }
            }
            return null;
        }
    }
}
