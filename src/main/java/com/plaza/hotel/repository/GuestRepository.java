package com.plaza.hotel.repository;

import com.plaza.hotel.dto.GuestResponseDTO;
import com.plaza.hotel.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query("""
        SELECT new com.plaza.hotel.dto.GuestResponseDTO(
             g.id,
             g.name,
             g.document,
             g.phoneNumber,
             COALESCE(SUM(a.totalValue), 0),
             COALESCE(MAX(a.totalValue) FILTER (WHERE a.checkInDate = (
                SELECT MAX(a2.checkInDate) FROM Accommodation a2 WHERE a2.guest = g)),
                0)
         )
        FROM Guest g
        LEFT JOIN g.accommodations a
        WHERE (cast(:name as string) IS NULL OR g.name LIKE %:name%)
        AND (:document IS NULL OR g.document = :document)
        AND (:phoneNumber IS NULL OR g.phoneNumber = :phoneNumber)
        AND (:inHotel IS NULL
        OR (:inHotel = true AND EXISTS (
            SELECT 1 FROM Accommodation a3
            WHERE a3.guest = g AND a3.checkOutDate IS NULL))
        OR (:inHotel = false AND NOT EXISTS (
            SELECT 1 FROM Accommodation a3
            WHERE a3.guest = g AND a3.checkOutDate IS NULL)
        AND EXISTS (
            SELECT 1 FROM Accommodation a3
            WHERE a3.guest = g)))
        GROUP BY g.id, g.name, g.document, g.phoneNumber
    """)
    List<GuestResponseDTO> search(
            @Param("name") String name,
            @Param("document") String document,
            @Param("phoneNumber") String phoneNumber,
            @Param("inHotel") Boolean inHotel);
}
