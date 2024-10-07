package com.pillgood.entity;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @Column(name = "chat_message_no")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_message_no_SEQ")
    @SequenceGenerator(name = "chat_message_no_SEQ", sequenceName = "chat_message_no_SEQ", allocationSize = 1)
    private Long chatMessageNo;
    private String chatMessageSenderId;
    private String chatMessageMessage;
    private Date chatMessageDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_no")
    private ChatRoom chatRoom;

    // Getters and setters
}
