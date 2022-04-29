package com.tranminhvuong.restful.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "create_at", nullable = false)
	private Date createdAt;

	@Column(name = "update_at", nullable = true)
	private Date updatedAt;

	@Column(name = "is_deleted", nullable = true)
	private Integer isDeleted;

	@Column(name = "deleted_at", nullable = true)
	private Date deletedAt;

	public BaseEntity() {
		this.createdAt = Calendar.getInstance().getTime();
		this.updatedAt = null;
		this.isDeleted = 0;
		this.deletedAt = null;
	}
}
