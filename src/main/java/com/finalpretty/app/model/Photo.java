package com.finalpretty.app.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Photo {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer photo_id;

    @Lob
    @Column(name = "filebyte")
    private byte[] filebyte;

    @Column(name = "path")
    private String path;

}
