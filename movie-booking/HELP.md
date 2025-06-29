For concurrent request :
seq 1 5 | xargs -P5 -I{} bash -c '
response=$(curl -X POST -s -w "HTTP %{http_code}" -o /dev/null \
"http://localhost:8092/api/customers/book?customerId=acae0ba8-4b00-42e0-9d15-91fb85134794&seatId=f854d1eb-d61e-4779-b988-af021b5c7abf");
echo "[{}] Request at $(date +%T): $response"
'


// Customer Id1 - acae0ba8-4b00-42e0-9d15-91fb85134794
// Movie Id - d382417a-1c50-4d0b-b785-80a967efdbd8

// Seat ID = f854d1eb-d61e-4779-b988-af021b5c7abf


// customer Id2 = b8195528-dd5a-4801-bb46-c1c94fff949f