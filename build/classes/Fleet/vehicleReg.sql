SELECT 
`vehicle_registration`.vehicle_ID, `vehicle_registration`.bill_ID, DATE_FORMAT(`vehicle_registration`.renewedDate,'%Y-%m-%d'),DATE_FORMAT(`vehicle_registration`.nextRenewDate,'%Y-%m-%d') 
 FROM vehicle_registration