         entry 
         addi   r13, r0, topaddr
         addi   r14, r0, topaddr
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 0
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 0
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, or_
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 0
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 1
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, or_
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 1
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 0
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, or_
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 1
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 1
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, or_
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 0
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 0
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, and_
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 0
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 1
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, and_
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 1
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 0
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, and_
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 1
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 1
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, and_
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 0
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 0
         sub    r2, r2, r2
         not    r2, r1
         bz     r2, 2036
         addi   r1, r0, 1
         j      2040
         addi   r1, r0, 0
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 0
         sub    r4, r4, r4
         addi   r4, r4, 0
         and    r2, r3, r4
         bz     r2, 2136
         addi   r2, r0, 1
         j      2140
         addi   r2, r0, 0
         add    r1, r2, r0
         sub    r2, r2, r2
         not    r2, r1
         bz     r2, 2164
         addi   r1, r0, 1
         j      2168
         addi   r1, r0, 0
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 0
         sub    r4, r4, r4
         addi   r4, r4, 1
         and    r2, r3, r4
         bz     r2, 2232
         addi   r2, r0, 1
         j      2236
         addi   r2, r0, 0
         add    r1, r2, r0
         sub    r2, r2, r2
         not    r2, r1
         bz     r2, 2260
         addi   r1, r0, 1
         j      2264
         addi   r1, r0, 0
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 1
         sub    r4, r4, r4
         addi   r4, r4, 0
         and    r2, r3, r4
         bz     r2, 2328
         addi   r2, r0, 1
         j      2332
         addi   r2, r0, 0
         add    r1, r2, r0
         sub    r2, r2, r2
         not    r2, r1
         bz     r2, 2356
         addi   r1, r0, 1
         j      2360
         addi   r1, r0, 0
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 1
         sub    r4, r4, r4
         addi   r4, r4, 1
         and    r2, r3, r4
         bz     r2, 2424
         addi   r2, r0, 1
         j      2428
         addi   r2, r0, 0
         add    r1, r2, r0
         sub    r2, r2, r2
         not    r2, r1
         bz     r2, 2452
         addi   r1, r0, 1
         j      2456
         addi   r1, r0, 0
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 0
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 0
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, xor
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 0
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 1
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, xor
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 1
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 0
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, xor
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % Function evaluation begin.

         % Allocating space for return value
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Backing up registers the 11 general registers + 1 stack frame pointer
         sw     0(r13), r1 % Backing up r1
         sw     -4(r13), r2 % Backing up r2
         sw     -8(r13), r3 % Backing up r3
         sw     -12(r13), r4 % Backing up r4
         sw     -16(r13), r5 % Backing up r5
         sw     -20(r13), r6 % Backing up r6
         sw     -24(r13), r7 % Backing up r7
         sw     -28(r13), r8 % Backing up r8
         sw     -32(r13), r9 % Backing up r9
         sw     -36(r13), r10 % Backing up r10
         sw     -40(r13), r11 % Backing up r11
         addi   r13, r13, -44
         sw     0(r13), r15 % Backing up return pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r12 % Backing up method pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r14 % Backing up stack frame pointer
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 1
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3
         addi   r3, r3, 1
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, xor
         subi   r13, r13, -4
         add    r13, r14, r0 % Restoring stack pointer. stack point = stack frame pointer
         lw     r14, 4(r13) % Restoring stack frame pointer
         lw     r12, 8(r13) % Restoring method pointer
         lw     r15, 12(r13) % Restoring return pointer
         lw     r11, 16(r13) % Restoring general register r11
         lw     r10, 20(r13) % Restoring general register r10
         lw     r9, 24(r13) % Restoring general register r9
         lw     r8, 28(r13) % Restoring general register r8
         lw     r7, 32(r13) % Restoring general register r7
         lw     r6, 36(r13) % Restoring general register r6
         lw     r5, 40(r13) % Restoring general register r5
         lw     r4, 44(r13) % Restoring general register r4
         lw     r3, 48(r13) % Restoring general register r3
         lw     r2, 52(r13) % Restoring general register r2
         lw     r1, 56(r13) % Restoring general register r1
         addi   r13, r13, 56
         subi   r13, r13, -4

         % Returned procedure over.
         lw     r1, 0(r13) % Loading up the function returned value into a register
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1
         addi   r1, r1, 9999
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 2
         sub    r4, r4, r4
         addi   r4, r4, 3
         mul    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         sub    r5, r5, r5
         sub    r6, r6, r6
         addi   r6, r6, 5
         sub    r7, r7, r7
         addi   r7, r7, 3
         mul    r5, r6, r7
         sub    r6, r6, r6
         sub    r7, r7, r7
         addi   r7, r7, 3
         add    r6, r7, r5
         sub    r5, r5, r5
         sub    r7, r7, r7
         addi   r7, r7, 3
         sub    r8, r8, r8
         sub    r9, r9, r9
         sub    r10, r10, r10
         addi   r10, r10, 2
         sub    r11, r11, r11
         addi   r11, r11, 1
         add    r9, r10, r11
         sub    r10, r10, r10
         sub    r11, r11, r11
         addi   r11, r11, 3
         add    r10, r11, r9
         sub    r9, r9, r9
         sub    r11, r11, r11
         addi   r11, r11, 2
         add    r9, r11, r10
         add    r8, r9, r0
         sub    r9, r9, r9
         muli   r9, r8, 2
         sub    r8, r8, r9
         mul    r5, r7, r8
         sub    r7, r7, r7
         sub    r8, r8, r8
         addi   r8, r8, 4
         add    r7, r8, r5
         sub    r5, r5, r5
         sub    r5, r6, r7
         add    r4, r5, r0
         sub    r5, r5, r5
         muli   r5, r4, 2
         sub    r4, r4, r5
         mul    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 3
         add    r2, r4, r3
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, 0 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sub    r3, r3, r3
         muli   r3, r2, 2
         sub    r2, r2, r3
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3
         addi   r3, r3, 3
         sub    r4, r4, r4
         addi   r4, r4, 10
         mul    r2, r3, r4
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 2
         div    r3, r2, r4
         sub    r2, r2, r2
         sub    r4, r4, r4
         addi   r4, r4, 4
         add    r2, r3, r4
         add    r1, r1, r14
         sw     0(r1), r2

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -8 % debug3
         sub    r2, r2, r2
         sub    r3, r3, r3
         sub    r4, r4, r4
         addi   r4, r4, 1
         sub    r5, r5, r5
         addi   r5, r5, 2
         add    r3, r4, r5
         sub    r4, r4, r4
         sub    r5, r5, r5
         sub    r6, r6, r6
         sub    r7, r7, r7
         addi   r7, r7, 4
         sub    r8, r8, r8
         addi   r8, r8, 6
         add    r6, r7, r8
         add    r5, r6, r0
         sub    r6, r6, r6
         sub    r6, r6, r6
         muli   r6, r5, 2
         sub    r5, r5, r6
         sub    r6, r6, r6
         muli   r6, r5, 2
         sub    r5, r5, r6
         sub    r6, r6, r6
         sub    r6, r6, r6
         muli   r6, r5, 2
         sub    r5, r5, r6
         sub    r4, r3, r5
         add    r2, r4, r0
         sub    r3, r3, r3
         muli   r3, r2, 2
         sub    r2, r2, r3
         sub    r3, r3, r3
         muli   r3, r2, 2
         sub    r2, r2, r3
         add    r1, r1, r14
         sw     0(r1), r2
         sub    r1, r1, r1

         % debug4

         % identifier == null debug
         sub    r2, r2, r2
         addi   r2, r0, 0 % debug3
         add    r2, r2, r14 % Calculate address to variable and set it up into a register
         lw     r1, 0(r2) % Load variable
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % debug4

         % identifier == null debug
         sub    r2, r2, r2
         addi   r2, r0, -4 % debug3
         add    r2, r2, r14 % Calculate address to variable and set it up into a register
         lw     r1, 0(r2) % Load variable
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         sub    r1, r1, r1

         % debug4

         % identifier == null debug
         sub    r2, r2, r2
         addi   r2, r0, -8 % debug3
         add    r2, r2, r14 % Calculate address to variable and set it up into a register
         lw     r1, 0(r2) % Load variable
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         hlt   
 or_     sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r2, r2, r2
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, 0 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sub    r4, r4, r4

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, -4 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         or     r2, r3, r4
         bz     r2, 4208
         addi   r2, r0, 1
         j      4212
         addi   r2, r0, 0
         sw     0(r1), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15
 and_    sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r3, r3, r3
         sub    r4, r4, r4

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, 0 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         sub    r5, r5, r5

         % debug4

         % identifier == null debug
         sub    r6, r6, r6
         addi   r6, r0, -4 % debug3
         add    r6, r6, r14 % Calculate address to variable and set it up into a register
         lw     r5, 0(r6) % Load variable
         and    r3, r4, r5
         bz     r3, 4288
         addi   r3, r0, 1
         j      4292
         addi   r3, r0, 0
         sw     0(r1), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15
 nand    sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r4, r4, r4
         sub    r5, r5, r5
         sub    r6, r6, r6

         % debug4

         % identifier == null debug
         sub    r7, r7, r7
         addi   r7, r0, 0 % debug3
         add    r7, r7, r14 % Calculate address to variable and set it up into a register
         lw     r6, 0(r7) % Load variable
         sub    r7, r7, r7

         % debug4

         % identifier == null debug
         sub    r8, r8, r8
         addi   r8, r0, -4 % debug3
         add    r8, r8, r14 % Calculate address to variable and set it up into a register
         lw     r7, 0(r8) % Load variable
         and    r5, r6, r7
         bz     r5, 4372
         addi   r5, r0, 1
         j      4376
         addi   r5, r0, 0
         add    r4, r5, r0
         sub    r5, r5, r5
         not    r5, r4
         bz     r5, 4400
         addi   r4, r0, 1
         j      4404
         addi   r4, r0, 0
         sw     0(r1), r4 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15
 xor     addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -8 % debug3
         sub    r5, r5, r5
         sub    r6, r6, r6

         % debug4

         % identifier == null debug
         sub    r7, r7, r7
         addi   r7, r0, 0 % debug3
         add    r7, r7, r14 % Calculate address to variable and set it up into a register
         lw     r6, 0(r7) % Load variable
         sub    r7, r7, r7

         % debug4

         % identifier == null debug
         sub    r8, r8, r8
         addi   r8, r0, -4 % debug3
         add    r8, r8, r14 % Calculate address to variable and set it up into a register
         lw     r7, 0(r8) % Load variable
         or     r5, r6, r7
         bz     r5, 4484
         addi   r5, r0, 1
         j      4488
         addi   r5, r0, 0
         sub    r6, r6, r6
         sub    r7, r7, r7
         sub    r8, r8, r8
         sub    r9, r9, r9

         % debug4

         % identifier == null debug
         sub    r10, r10, r10
         addi   r10, r0, 0 % debug3
         add    r10, r10, r14 % Calculate address to variable and set it up into a register
         lw     r9, 0(r10) % Load variable
         sub    r10, r10, r10

         % debug4

         % identifier == null debug
         sub    r11, r11, r11
         addi   r11, r0, -4 % debug3
         add    r11, r11, r14 % Calculate address to variable and set it up into a register
         lw     r10, 0(r11) % Load variable
         and    r8, r9, r10
         bz     r8, 4556
         addi   r8, r0, 1
         j      4560
         addi   r8, r0, 0
         add    r7, r8, r0
         sub    r8, r8, r8
         not    r8, r7
         bz     r8, 4584
         addi   r7, r0, 1
         j      4588
         addi   r7, r0, 0
         and    r6, r5, r7
         bz     r6, 4604
         addi   r6, r0, 1
         j      4608
         addi   r6, r0, 0
         add    r1, r1, r14
         sw     0(r1), r6
         sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r5, r5, r5

         % debug4

         % identifier == null debug
         sub    r6, r6, r6
         addi   r6, r0, -8 % debug3
         add    r6, r6, r14 % Calculate address to variable and set it up into a register
         lw     r5, 0(r6) % Load variable
         sw     0(r1), r5 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15
