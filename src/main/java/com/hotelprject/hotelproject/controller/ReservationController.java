package com.hotelprject.hotelproject.controller;

import com.hotelprject.hotelproject.model.HotelUser;
import com.hotelprject.hotelproject.model.Reservation;
import com.hotelprject.hotelproject.model.Room;
import com.hotelprject.hotelproject.service.ReservationService;
import com.hotelprject.hotelproject.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final RoomService roomService;
    private final ReservationService reservationService;

    @GetMapping("/reserve")
    public String showReservationPage(@RequestParam("roomId") Long roomId, Model model) {
        // Oda bilgisini RoomService üzerinden alıyoruz.
        Room room = roomService.getRoomById(roomId);

        // Oda bulunamazsa hata sayfası gösterilebilir
        if (room == null) {
            return "error";  // error.html sayfası
        }

        // Oda bilgisini model ile şablona gönderiyoruz
        model.addAttribute("room", room);
        return "reserve";  // reserve.html şablonunu döndür
    }

    @PostMapping("/reserve")
    public String reserveRoom(@RequestParam("roomId") Long roomId,
                              @RequestParam("reservationDate") String reservationDate,
                              @RequestParam("endDate") String endDate,
                              @RequestParam("numOfPeople") int numOfPeople,
                              HttpSession httpSession,
                              Model model) {

        // Oda bilgilerini al
        Room room = roomService.getRoomById(roomId);
        room.setAvailable(Boolean.FALSE);

        roomService.saveRoom(room);

        var user = (String) httpSession.getAttribute("loggedInUser");

        Reservation reservation = reservationService.makeReservation(roomId, LocalDate.parse(reservationDate), LocalDate.parse(endDate), user);

        Double totalPrice = ChronoUnit.DAYS.between(reservation.getReservationDate(), reservation.getEndDate()) * room.getPrice();
        // Rezervasyon işlemini burada işleyebilirsiniz. Örneğin, bir rezervasyon objesi oluşturup veritabanına kaydedebilirsiniz.

        model.addAttribute("room", room);
        model.addAttribute("reservationDate", reservationDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("numOfPeople", numOfPeople);

        // Rezervasyon işlemi başarılı olduğunda kullanıcıyı bilgilendirme
        model.addAttribute("message", "Your reservation has been successfully made.");
        model.addAttribute("room", room);
        model.addAttribute("reservation", reservation);
        model.addAttribute("totalPrice", totalPrice);
        return "payment";  // Rezervasyon başarı sayfası
    }
}

