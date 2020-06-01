using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace KryptografiaProjektKoncowy.Models
{
    public class User
    {
        public static int UsersNumber { get; set; } = 0;
        public int Id { get; set; } = 0;
        public int Gx { get; set; }
        public int Gcy { get; set; } = -1;
        public int token { get; set; }

        public User()
        {
            UsersNumber++;
            Id = UsersNumber;
        }

    }
}
