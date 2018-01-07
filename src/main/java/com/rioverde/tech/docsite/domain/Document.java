package com.rioverde.tech.docsite.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

import javax.persistence.*;

@Data
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String summary;

    private int versionMajor;

    private int versionMinor;

    @ManyToOne
    private Authority authority;

    @OneToOne(fetch = FetchType.EAGER)
    private User createdBy;

    @OneToOne(fetch = FetchType.EAGER)
    private User UpdatedBy;

    @CreationTimestamp
    private Date createdDateTime;

    @UpdateTimestamp
    private Date updatedDateTime;

    @Lob
    private Byte[] document;
}
