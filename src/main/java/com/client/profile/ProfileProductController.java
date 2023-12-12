package com.client.profile;

import com.common.models.Product;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProfileProductController {
    @FXML
    private Text title;
    @FXML
    private ImageView image;

    @FXML
    private Text price;

    public void setData(Product product) {
        title.setText(product.getTitle());
        image.setImage(product.getImage());
        price.setText((int)product.getPrice() + " грн.");
    }
}
