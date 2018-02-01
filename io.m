get		addi r2, r0, 1000
getl	bz r2, geto
		getc r1
		subi r1, r1, 48
		mul  r3, r1, r2
		add  r4, r4, r3
		divi r2, r2, 10
		j getl
geto	sw 0(r13), r4 
		jr r15
put		lw r1, 0(r13)
        addi r2, r0, 1000
putl	bz	r2, puto
		div r3, r1, r2
		modi r3, r3, 10
		divi r2, r2, 10
		addi r3, r3, 48
		putc r3
		j putl
puto	addi r3, r0, 10
		putc r3 % New line.
		jr r15