package models.handlers;

import javafx.scene.control.TextArea;

import java.sql.Connection;

/**
 * Created by boyanbonev on 09/01/2017.
 */
public class InsertIntoTablesHandler extends BaseHandler implements IInsertIntoTablesHandler {

    public InsertIntoTablesHandler(Connection _connection, TextArea _textArea) {
        super(_textArea, _connection);
    }

    @Override
    public void Handle() {
        String[] children = new String[1000];
        String[] santasLittleHelpers = new String[10];
        String[] gifts = new String[10];
        String[] presents = new String[100];

        for(int i = 1; i <= 1000; i++){
            int id = i;
            String name;
            String address;

            if(id != 1) {
                name = "'Name Child " + i + "'";
                address = "'Address Child " + i + "'";
            } else {
                name = "'Boyan Bonev'";
                address = "'160 Bournbrook Rd'";
            }

            children[i-1] = "INSERT INTO Child (cid, name, address) VALUES (" + id + ", " + name + ", " + address + ")";
        }

        for (int i = 1; i <= 10; i++) {
            int id = i;
            String name;

            if (id != 1) {
                name = "'Helper " + i + "'";
            } else {
                name = "'Harry'";
            }

            santasLittleHelpers[i-1] = "INSERT INTO SantasLittleHelper (Slhid, Name) VALUES (" + id + ", " + name + ")";
        }

        for (int i = 1; i <= 10; i++) {
            int id = i;
            String description;

            if(id == 1) {
                description = "'Car Toy'";
            } else if (id == 2){
                description = "'Christmas Socks'";
            } else {
                description = "'Description " + i + "'";
            }

            gifts[i-1] = "INSERT INTO Gift (Gid, Description) VALUES (" + id + ", " + description + ")";
        }

        for (int i = 1; i <= 100; i++) {

            int cid = i;
            int gid;
            int slhid;

            if(cid == 1){
                gid = 1;
                slhid = 1;
            } else if (cid == 2) {
                cid = 1;
                gid = 2;
                slhid = 1;
            } else {
                gid = i / 10 == 0 ? 1 : i /10;
                slhid = i / 10 == 0 ? 1 : i /10;
            }

            presents[i-1] = "INSERT INTO Present (Gid, Cid, Slhid) VALUES (" + gid + ", " + cid + ", " + slhid + ")";
        }

        for (String child: children){
            Execute(child);
        }

        for (String helper: santasLittleHelpers){
            Execute(helper);
        }

        for (String gift: gifts){
            Execute(gift);
        }

        for (String present: presents){
            Execute(present);
        }
    }
}
