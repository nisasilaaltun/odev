package com.hotelprject.hotelproject.controller;

import com.hotelprject.hotelproject.model.Reservation;
import com.hotelprject.hotelproject.model.Room;
import com.hotelprject.hotelproject.service.ReservationService;
import com.hotelprject.hotelproject.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        Room room = roomService.getRoomById(roomId);
        var user = (String) httpSession.getAttribute("loggedInUser");

        Double totalPrice = ChronoUnit.DAYS.between(LocalDate.parse(reservationDate), LocalDate.parse(endDate)) * room.getPrice();

        model.addAttribute("room", room);
        model.addAttribute("reservationDate", reservationDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("numOfPeople", numOfPeople);

        model.addAttribute("message", "Your reservation has been successfully made.");
        model.addAttribute("room", room);
        model.addAttribute("reservationDate", LocalDate.parse(reservationDate));
        model.addAttribute("endDate", LocalDate.parse(endDate));
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("name", user);
        return "payment";  // ödeme sayfasına yönlendirme
    }
}
