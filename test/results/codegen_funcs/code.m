
         % debug4

         % identifier == null debug
 A_meth1 sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r2, r2, r2

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, 0 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         add    r1, r1, r12 % Attribute assignment
         sw     0(r1), r2
         sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r2, r2, r2
         addi   r2, r2, 0
         sw     0(r1), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15
         entry 
         addi   r13, r0, topaddr
         addi   r14, r0, topaddr
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r3, r3, r3
         addi   r3, r3, 2
         add    r1, r1, r14
         sw     0(r1), r3

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r3, r3, r3
         addi   r3, r3, 4
         add    r1, r1, r14
         sw     0(r1), r3

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -12 % debug3
         sub    r3, r3, r3

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

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, 0 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Setting up parameter number 1
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 1
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r4, r4, r4
         addi   r4, r0, -4 % debug3
         add    r4, r4, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r4) % Load variable
         sw     0(r13), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 8 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, fun1
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
         lw     r3, 0(r13) % Loading up the function returned value into a register
         add    r1, r1, r14
         sw     0(r1), r3
         sub    r1, r1, r1

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -12 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r1, 0(r3) % Load variable
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -8 % debug3
         sub    r3, r3, r3

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

         % Placing the stack frame pointer
         addi   r14, r13, 0 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, fun4
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
         add    r3, r13, r0 % Loading up address of function's return value (function returned an object.)
         add    r1, r1, r14

         % Copying object with size 4
         sub    r4, r4, r4
         lw     r4, 0(r3) % Copying.. (loading)
         sw     0(r1), r4 % Copying..(saving)
         sub    r1, r1, r1

         % debug4

         % array register evaluation begin 2

         % array register evaluation end 2

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, -8 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r1, 0(r3) % Load variable
         sw     0(r13), r15
         addi   r13, r13, -4 %  Increasing stack pointer by 4
         sw     0(r13), r1
         jl     r15, put
         subi   r13, r13, -4
         lw     r15, 0(r13)
         hlt   
 fun1    addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -8 % debug3
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
         add    r3, r4, r5
         add    r1, r1, r14
         sw     0(r1), r3
         sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r3, r3, r3
         sub    r4, r4, r4

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, -8 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r4, 0(r5) % Load variable
         sub    r5, r5, r5

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

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, 0 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 4 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, fun2
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
         lw     r5, 0(r13) % Loading up the function returned value into a register
         add    r3, r4, r5
         sub    r4, r4, r4
         sub    r5, r5, r5

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

         % debug4

         % identifier == null debug
         sub    r3, r3, r3
         addi   r3, r0, 0 % debug3
         add    r3, r3, r14 % Calculate address to variable and set it up into a register
         lw     r2, 0(r3) % Load variable
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 4 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, fun3
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
         lw     r5, 0(r13) % Loading up the function returned value into a register
         add    r4, r3, r5
         sw     0(r1), r4 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15
 fun2    sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r3, r3, r3

         % debug4

         % identifier == null debug
         sub    r5, r5, r5
         addi   r5, r0, 0 % debug3
         add    r5, r5, r14 % Calculate address to variable and set it up into a register
         lw     r3, 0(r5) % Load variable
         sw     0(r1), r3 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15

         % debug4

         % identifier == null debug
 fun3    sub    r1, r1, r1
         addi   r1, r0, 0 % debug3
         sub    r5, r5, r5
         addi   r5, r5, 5
         add    r1, r1, r14
         sw     0(r1), r5
         sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r5, r5, r5

         % debug4

         % identifier == null debug
         sub    r6, r6, r6
         addi   r6, r0, 0 % debug3
         add    r6, r6, r14 % Calculate address to variable and set it up into a register
         lw     r5, 0(r6) % Load variable
         sw     0(r1), r5 % Saving value found at the address inside right hand side register to address in left hand side.
         jr     r15
 fun4    addi   r13, r13, -4 %  Increasing stack pointer by 4
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % debug4

         % identifier == null debug
         sub    r1, r1, r1
         addi   r1, r0, -4 % debug3
         sub    r6, r6, r6

         % Function evaluation begin.

         % debug4

         % identifier == null debug
         sub    r7, r7, r7
         addi   r7, r0, 0 % debug3

         % Function evaluation ends.

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
         add    r12, r14, r7 % The function that is about to be called is a method. We need to put a pointer to the instance in a regsiter

         % Setting up parameter number 0
         sub    r1, r1, r1
         add    r1, r13, r0 % Setting up address for parameter 0
         sub    r2, r2, r2
         addi   r2, r2, 99
         sw     0(r13), r2 % Saving value found at the address inside right hand side register to address in left hand side.
         addi   r13, r13, -4 %  Increasing stack pointer by 4

         % Placing the stack frame pointer
         addi   r14, r13, 4 % here

         % Stack frame pointer will now point to top of stack.

         % Load up the code return address
         jl     r15, A_meth1
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
         lw     r6, 0(r13) % Loading up the function returned value into a register
         add    r1, r1, r14
         sw     0(r1), r6
         sub    r1, r1, r1

         % Calculating address for return space (stackFramePointer  + bypassing backuped registers).
         addi   r1, r14, 60 % Setting up register holding address to return space
         sub    r6, r6, r6

         % debug4

         % identifier == null debug
         sub    r8, r8, r8
         addi   r8, r0, 0 % debug3
         add    r8, r8, r14 % Calculate address to variable and set it up into a register
         add    r6, r8, r0 % Load variable

         % Copying object with size 4
         sub    r9, r9, r9
         lw     r9, 0(r6) % Copying.. (loading)
         sw     0(r1), r9 % Copying..(saving)
         jr     r15
